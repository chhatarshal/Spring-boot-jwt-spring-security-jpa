package com.myapp.base;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.myapp.base.entity.User;
import com.myapp.base.model.UserModel;

public class PostDtoUnitTest {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertPostEntityToPostDto_thenCorrect() {
        User post = new User();
        post.setId(1L);
        post.setUserName("dumm");
        UserModel postDto = modelMapper.map(post, UserModel.class);
        assertEquals(post.getId(), postDto.getId());
        assertEquals(post.getUserName(), postDto.getUserName());
    }
    
}