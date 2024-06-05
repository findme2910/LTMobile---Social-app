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
import com.example.myapplication.network.model.dto.CommentViewDTO;
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
    // Lấy postID từ Intent
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

        //lấy danh sách bình luận từ server
        CommentManager.getComment(postID, new HandleListener<List<CommentViewDTO>>() {

            @Override
            public void onSuccess(List<CommentViewDTO> commentViewDTOS) {
                for (CommentViewDTO v1 : commentViewDTOS) {
                    Comment comment = new Comment(
                            v1.getName(),
                            v1.getContent(),
                            v1.getAvatar(),
                            new Date(v1.getCreateAt()),
                            postID,
                            convertReplyDTOsToComments(v1.getReplys())
                    );
                    commentList.add(comment);
                }
                commentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(CommentsActivity.this, "Failed to load comments: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

// thêm bình luận mới vào danh sách
        buttonSend.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            String commentText = editTextComment.getText().toString().trim();
            if (!commentText.isEmpty()) {
                // Thêm bình luận mới vào danh sách
                Comment newComment = new Comment(username, commentText, avatarUrl, calendar.getTime(), postID,new ArrayList<>());

                // Gửi bình luận mới đến server
                CommentManager commentManager = new CommentManager();
                commentManager.addComment(postID, commentText, new HandleListener<String>() {
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
    private List<Comment> convertReplyDTOsToComments(List<CommentViewDTO> replyDTOs) {
        List<Comment> replies = new ArrayList<>();
        if (replyDTOs != null) {
            for (CommentViewDTO dto : replyDTOs) {
                Comment comment = new Comment(
                        dto.getName(),
                        dto.getContent(),
                        dto.getAvatar(),
                        new Date(dto.getCreateAt()),
                        postID,
                        convertReplyDTOsToComments(dto.getReplys())
                );
                replies.add(comment);
            }
        }
        return replies;
    }
}
