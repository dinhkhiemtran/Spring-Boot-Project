package com.khiemtran.springboot.security;

public interface Sanitizer<T> {
  T sanitize(T t);
}
