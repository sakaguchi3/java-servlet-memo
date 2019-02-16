CREATE TABLE IF NOT EXISTS memo_data (
    memo_id INT(11) AUTO_INCREMENT NOT NULL COMMENT 'ID',
    category INT(11) COMMENT 'カテゴリ',
    title VARCHAR(64) COMMENT 'タイトル',
    memo TEXT COMMENT 'メモ',
    create_date DATETIME COMMENT '作成日',
    modified_date DATETIME COMMENT '更新日',
    PRIMARY KEY (memo_id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8