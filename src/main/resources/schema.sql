-- `chat` テーブルを作成
CREATE TABLE chat(
-- `id` カラム: 主キー、自動増加
id INT  PRIMARY KEY AUTO_INCREMENT,
-- `name` カラム: 100文字までの文字列
name VARCHAR(100),
--'comment'カラム：500文字までの文字列
comment VARCHAR(500)
);