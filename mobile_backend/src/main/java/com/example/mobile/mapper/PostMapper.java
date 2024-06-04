package com.example.mobile.mapper;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.example.mobile.config.ConvertFile;
import com.example.mobile.dto.PostViewDTO;
import com.example.mobile.model.Post;

@Mapper(componentModel = "spring")
public abstract class PostMapper {
	public static PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);



	@Mapping(source = "id", target = "postId")
	@Mapping(source = "user.id", target = "userId")
	@Mapping(source = "user.avatar", target = "avatarUser", qualifiedByName = "blobToString")
	@Mapping(source = "image", target = "image", qualifiedByName = "blobToString")
	@Mapping(expression = "java(post.getLikes().size())", target = "numberLike")
	@Mapping(expression = "java(post.getComments().size())", target = "numberComment")
	@Mapping(source = "createAt", target = "createAt", qualifiedByName = "dateToMillis")
	public abstract PostViewDTO entityToViewPostDTO(Post post);

	@Named("dateToMillis")
	public long dateToMillis(Date date) {
		return date.getTime();
	}

	@Named("blobToString")
	public String blodToString(Blob blob) {
		return ConvertFile.toString(blob);
	}
}
