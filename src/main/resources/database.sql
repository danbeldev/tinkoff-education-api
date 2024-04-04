CREATE TABLE users
(
    id integer generated always as identity,
    username varchar(48) unique not null,
    first_name varchar(48) not null,
    last_name varchar(48) not null,
    middle_name varchar(48) default null,
    password varchar(64) not null,
    balance real not null default 0.0,
    role varchar(32) not null,

    CONSTRAINT PK__users__key PRIMARY KEY(id)
);

CREATE TABLE categories
(
    id integer generated always as identity,
    name varchar(64) unique not null,

    CONSTRAINT PK__categories__key PRIMARY KEY(id)
);

CREATE TABLE courses
(
    id integer generated always as identity,
    back_image_id varchar(48) not null,
    title varchar(48) not null,
    description varchar(256) not null,
    price real not null default 0.0,
    category_id integer not null,

    CONSTRAINT PK__courses__key PRIMARY KEY(id),
    CONSTRAINT FK__courses__category FOREIGN KEY(category_id) REFERENCES categories(id)
);

CREATE TABLE students_courses_subscribers
(
    user_id integer not null,
    course_id integer not null,

    CONSTRAINT PK__students_courses__key PRIMARY KEY(user_id, course_id),
    CONSTRAINT FK__students_courses__user FOREIGN KEY(user_id) REFERENCES users(id),
    CONSTRAINT FK__students_courses__course FOREIGN KEY(course_id) REFERENCES courses(id)
);

CREATE TABLE course_videos
(
    id integer generated always as identity,
    course_id integer not null,
    video_id varchar(48) not null,
    title varchar(48) not null,
    description varchar(256) not null,
    date date not null,

    CONSTRAINT PK__course_videos__key PRIMARY KEY(id),
    CONSTRAINT FK__course_videos__course FOREIGN KEY(course_id) REFERENCES courses(id)
);

CREATE TABLE course_video_progress
(
    user_id integer not null,
    course_video_id integer not null,

    CONSTRAINT PK__course_video_progress__key PRIMARY KEY(user_id, course_video_id),
    CONSTRAINT FK__course_video_progress__user FOREIGN KEY(user_id) REFERENCES users(id),
    CONSTRAINT FK__course_video_progress__course_video FOREIGN KEY(course_video_id) REFERENCES course_videos(id)
);