package com.blog.app.services;

import com.blog.app.payloads.Commentdto;

public interface CommentService {
Commentdto createComment(Commentdto commentdto,Integer pid, Integer id);
void deleteComment(Integer id);
}
