package com.example.mobile.mapper;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.example.mobile.config.ConvertFile;
import com.example.mobile.dto.FriendViewDTO;
import com.example.mobile.dto.FriendViewRequestDTO;
import com.example.mobile.dto.NotificationDTO;
import com.example.mobile.dto.ProfileDTO;
import com.example.mobile.dto.UserInformationDTO;
import com.example.mobile.model.FriendRequest;
import com.example.mobile.model.Notification;
import com.example.mobile.model.NotificationType;
import com.example.mobile.model.Post;
import com.example.mobile.model.User;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
	public static UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mapping(source = "createAt", target = "time", qualifiedByName = "dateToMillis")
	@Mapping(source = "fromUser.name", target = "name")
	@Mapping(source = "fromUser.id", target = "userId")
	@Mapping(source = "fromUser.avatar", target = "avatar", qualifiedByName = "blobToString")
	public abstract FriendViewRequestDTO friendRequestEntityToDTO(FriendRequest entity);

	@Mapping(source = "name", target = "name")
	@Mapping(source = "id", target = "userId")
	@Mapping(source = "avatar", target = "avatar", qualifiedByName = "blobToString")
	public abstract FriendViewDTO userEntityToDTO(User entity);

	@Mapping(source = "trigger.name", target = "name")
	@Mapping(source = "trigger.id", target = "userId")
	@Mapping(source = "trigger.avatar", target = "avatar", qualifiedByName = "blobToString")
	@Mapping(source = "createAt", target = "createAt", qualifiedByName = "dateToMillis")
	@Mapping(source = "post", target = "postId", qualifiedByName = "postToPostId")
	@Mapping(source = "type", target = "type", qualifiedByName = "enumToString")
	public abstract NotificationDTO NotificationToDTO(Notification entity);

	@Mapping(source = "id", target = "userId")
	@Mapping(source = "birth", target = "birth", qualifiedByName = "dateToMillis")
	@Mapping(source = "avatar", target = "avatar", qualifiedByName = "blobToString")
	@Mapping(source = "friends", target = "friends", qualifiedByName = "toListFriendView")
	public abstract ProfileDTO userToProfileDTO(User entity);

	@Named("enumToString") 
	public String enumToString(NotificationType type) {
		if (type != null) {
			return type.name();
		}
		return null;
	}

	@Named("postToPostId")
	public int postToPostId(Post post) {
		if (post != null)
			return post.getId();
		return 0;
	}

	@Named("toListFriendView")
	public List<FriendViewDTO> usersToFriendViewsDTO(List<User> entitys) {
		List<FriendViewDTO> list = new ArrayList<>();
		for (int i = 0; i < Math.min(5, entitys.size()); i++) {
			User u = entitys.get(i);
			list.add(FriendViewDTO.builder().avatar(blodToString(u.getAvatar())).userId(u.getId()).name(u.getName())
					.build());
		}
		return list;
	}

	@Mapping(source = "name", target = "name")
	@Mapping(source = "id", target = "userId")
	@Mapping(source = "avatar", target = "avatar", qualifiedByName = "blobToString")
	public abstract UserInformationDTO entityToUserInformationDTO(User user);

	@Named("dateToMillis")
	public long dateToMillis(Date date) {
		return date.getTime();
	}

	@Named("blobToString")
	public String blodToString(Blob blob) {
		return ConvertFile.toString(blob);
	}
}
