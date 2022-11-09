-- auto Generated on 2022-07-26
-- DROP TABLE IF EXISTS login_user;
CREATE TABLE login_user(
	user_id INT (11) UNIQUE NOT NULL AUTO_INCREMENT COMMENT '用户ID',
	user_name VARCHAR (50) NOT NULL COMMENT 'userName',
	birth DATETIME NOT NULL COMMENT 'birth',
	salary DECIMAL (13,4) NOT NULL COMMENT '工资',
	PRIMARY KEY (user_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'login_user';
