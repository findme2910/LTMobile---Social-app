package com.example.myapplication.ui.activities;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.model.Comment.Comment;
import com.example.myapplication.network.api.Comment.CommentManager;
import com.example.myapplication.network.api.HandleListener;
import com.example.myapplication.ui.adapters.comment.CommentAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CommentsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewComments;
    private CommentAdapter commentAdapter;
    private List<Comment> commentList;
    private EditText editTextComment;
    private Button buttonSend;
    private int postID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
    // Lấy postID từ Intent, chưa xử lý
        Intent intent = getIntent();
        postID = intent.getIntExtra("postID", -1);


        recyclerViewComments = findViewById(R.id.recyclerViewComments);
        editTextComment = findViewById(R.id.editTextComment);
        buttonSend = findViewById(R.id.buttonSend);

        commentList = new ArrayList<>();
        commentAdapter = new CommentAdapter(this, commentList);

        recyclerViewComments.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewComments.setAdapter(commentAdapter);
        //lấy dữ liệu người dùng và avatar
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        //nếu username không tồn tại thì sẽ trả vế unknown User
        String username = sharedPreferences.getString("username", "Unknown User");
        String avatarUrl = sharedPreferences.getString("avatarUrl", "");

//        addDummyData();
// thêm bình luận mới vào danh sách
        buttonSend.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            String commentText = editTextComment.getText().toString().trim();
            if (!commentText.isEmpty()) {
                // Thêm bình luận mới vào danh sách
                Comment newComment = new Comment(username, commentText, avatarUrl, calendar.getTime(), 2,new ArrayList<>());

                // Gửi bình luận mới đến server
                CommentManager commentManager = new CommentManager();
                commentManager.addComment(newComment.getPostID(), commentText, new HandleListener<String>() {
                    @Override
                    public void onSuccess(String result) {
                        // Thêm bình luận vào danh sách và cập nhật giao diện sau khi gửi thành công
                        commentList.add(newComment);
                        //đồng bộ recycleview
                        commentAdapter.notifyItemInserted(commentList.size() - 1);
                        //cuộn đến vị trí cuối cùng
                        recyclerViewComments.scrollToPosition(commentList.size() - 1);
                        editTextComment.setText(""); // Xóa văn bản sau khi gửi bình luận
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        // Xử lý lỗi khi gửi bình luận
                        Toast.makeText(CommentsActivity.this, "Failed to add comment: " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

//    private void addDummyData() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2023, Calendar.OCTOBER, 30);
//
//        // Bình luận 1
//        Comment comment1 = new Comment("User1", "This is a comment.", "", calendar.getTime(), 1,new ArrayList<>());
//        Comment reply1_1 = new Comment("User2", "This is a reply to comment 1.", "", calendar.getTime(),1, new ArrayList<>());
//        Comment reply1_2 = new Comment("User3", "This is another reply to comment 1.", "", calendar.getTime(), 1,new ArrayList<>());
//        comment1.getReplies().add(reply1_1);
//        comment1.getReplies().add(reply1_2);
//
//        // Bình luận 2
//        Comment comment2 = new Comment("User4", "This is another comment.", "", calendar.getTime(),1, new ArrayList<>());
//        Comment reply2_1 = new Comment("User5", "This is a reply to comment 2.", "", calendar.getTime(),1, new ArrayList<>());
//        comment2.getReplies().add(reply2_1);
//
//        // Thêm bình luận vào danh sách
//        commentList.add(comment1);
//        commentList.add(comment2);
//
//        commentAdapter.notifyDataSetChanged();}
}
