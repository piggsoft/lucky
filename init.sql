USE lucky;
DROP TABLE  IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id`               int            NOT NULL  AUTO_INCREMENT PRIMARY KEY ,
  `username`         VARCHAR(50)    NOT NULL  COMMENT '用户名',
  `nickname`         VARCHAR(50)    NULL      COMMENT '昵称'
);