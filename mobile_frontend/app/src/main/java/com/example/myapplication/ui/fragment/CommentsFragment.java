package com.example.myapplication.ui.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

public class CommentsFragment extends Fragment {

    private RecyclerView recyclerViewComments;
    private CommentAdapter commentAdapter;
    private List<Comment> commentList;
    private EditText editTextComment;
    private Button buttonSend;
    private int postID;
    private String username;
    private String avatarUrl;

    public CommentsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comments, container, false);

        recyclerViewComments = view.findViewById(R.id.recyclerViewComments);
        editTextComment = view.findViewById(R.id.editTextComment);
        buttonSend = view.findViewById(R.id.buttonSend);

        commentList = new ArrayList<>();
        commentAdapter = new CommentAdapter(getContext(), commentList);

        recyclerViewComments.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewComments.setAdapter(commentAdapter);

        // Lấy postID từ arguments


        if (getArguments() != null) {
            postID = getArguments().getInt("postID", -1);
        }

        // Lấy dữ liệu người dùng và avatar
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "Unknown User");
        avatarUrl = sharedPreferences.getString("avatarUrl", "");

        // Lấy danh sách bình luận từ server
        loadComments();

        // Thêm bình luận mới vào danh sách
        buttonSend.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            String commentText = editTextComment.getText().toString().trim();
            if (!commentText.isEmpty()) {
                // Thêm bình luận mới vào danh sách, chưa có id
                Comment newComment = new Comment(username, commentText, avatarUrl, calendar.getTime(), postID, new ArrayList<>());

                // Gửi bình luận mới đến server
                CommentManager commentManager = new CommentManager();
                commentManager.addComment(postID, commentText, new HandleListener<String>() {
                    @Override
                    public void onSuccess(String result) {
                        // Thêm bình luận vào danh sách và cập nhật giao diện sau khi gửi thành công
                        commentList.add(newComment);
                        // Đồng bộ RecyclerView
                        commentAdapter.notifyItemInserted(commentList.size() - 1);
                        // Cuộn đến vị trí cuối cùng
                        recyclerViewComments.scrollToPosition(commentList.size() - 1);
                        editTextComment.setText(""); // Xóa văn bản sau khi gửi bình luận
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        // Xử lý lỗi khi gửi bình luận
                        Toast.makeText(getContext(), "Failed to add comment: " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }

    private void loadComments() {
        CommentManager.getComment(postID, new HandleListener<List<CommentViewDTO>>() {

            @Override
            public void onSuccess(List<CommentViewDTO> commentViewDTOS) {
                for (CommentViewDTO v1 : commentViewDTOS) {
                    Comment comment = new Comment(
                            v1.getCommentId(),
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
                Toast.makeText(getContext(), "Failed to load comments: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<Comment> convertReplyDTOsToComments(List<CommentViewDTO> replyDTOs) {
        List<Comment> replies = new ArrayList<>();
        if (replyDTOs != null) {
            for (CommentViewDTO dto : replyDTOs) {
                Comment comment = new Comment(
                        dto.getCommentId(),
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
