create table movies(
    id serial primary key,
    title text,
    description text,
    genre text,
    year integer
);

create table rating(
    id serial primary key,
    movie_id integer references movies(id),
    rating integer check(rating > 0 and rating < 11)
);

create table review(
    id serial primary key,
    movie_id integer references movies(id),
    review_text text
);