package com.example.mobile.mapper;

import java.sql.Blob;
import java.util.Date;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.example.mobile.config.ConvertFile;
import com.example.mobile.dto.IfReqAddFiendDTO;
import com.example.mobile.model.FriendRequest;
import com.example.mobile.model.User;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
	public static UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mapping(source = "createAt", target = "time", qualifiedByName = "dateToMillis")
	@Mapping(source = "fromUser.name", target = "name")
	@Mapping(source = "fromUser.id", target = "userId")
	@Mapping(source = "fromUser.avatar", target = "avatar", qualifiedByName = "blobToString")
	public abstract IfReqAddFiendDTO friendRequestEntityToDTO(FriendRequest entity);

	@Mapping(source = "name", target = "name")
	@Mapping(source = "id", target = "userId")
	@Mapping(source = "avatar", target = "avatar", qualifiedByName = "blobToString")
	public abstract IfReqAddFiendDTO userEntityToDTO(User entity);

	@Named("dateToMillis")
	public long dateToMillis(Date date) {
		return date.getTime();
	}

	@Named("blobToString")
	public String blodToString(Blob blob) {
		return ConvertFile.toString(blob);
	}
}
