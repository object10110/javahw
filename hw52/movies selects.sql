insert into `movies`.`actor`(firstName, lastName, nationality, birth) values("actorFirstName2", "actorSecondName2", "unknown", "1955-10-10"), 
					("actorFirstName3", "actorSecondName3", "unknown", "1960-10-10"),("actorFirstName4", "actorSecondName4", "unknown", "1970-10-10"),("actorFirstName5", "actorSecondName5", "unknown", "1940-10-10"),
                    ("actorFirstName1", "actorSecondName1", "unknown", "1950-10-10");

insert into `movies`.`director`(firstName, lastName, nationality, birth) values("directorFirstName2", "directorSecondName2", "unknown", "1955-10-10"), 
					("directorFirstName3", "directorSecondName3", "unknown", "1960-10-10"),("directorFirstName4", "directorSecondName4", "unknown", "1970-10-10"),("directorFirstName5", "directorSecondName5", "unknown", "1940-10-10"),
                    ("directorFirstName1", "directorSecondName1", "unknown", "1950-10-10");

insert into `movies`.`movie`(directorId, title, releaseYear, rating, plot, movieLength) values (1, "film1", 2000-10-10, 5, "", "1:50:00"),(2, "film2", 2017-10-10, 4, "", "2:30:00"),
(1, "film3", 2002-10-10, 4, "", "1:30:00"),(2, "film4", 2015-10-10, 3, "", "1:35:00"),(4, "film5", 2006-10-10, 2, "", "1:40:00");


insert into `movies`.`genre`(`name`) values ("genre1"),("genre2"),("genre3");

insert into `movies`.`moviegenre` (movieId, genreId) values (1, 1), (1, 2), (2, 2),  (3, 2),  (3, 3);
insert into `movies`.`movieactor` (movieId, actorId) values (1, 1), (1, 2), (2, 2),  (3, 2),  (3, 3);

select * from `movies`.`actor`;

select * from `movies`.`movie` order by title desc;

select * from `movies`.`movie` order by releaseYear asc;

select * from `movies`.`movie` order by rating desc;

select * from `movies`.`movie` where year(releaseYear)=(year(now())-1);

select id,directorId, title, releaseYear, rating, plot, movieLength from `movies`.`movie`
JOIN `movies`.`moviegenre` ON id=movieId AND genreId=(SELECT id FROM `movies`.`genre` WHERE `name`="genre2"); 

select id,directorId, title, releaseYear, rating, plot, movieLength from `movies`.`movie`
JOIN `movies`.`movieactor` ON id=movieId AND actorId=(SELECT id FROM `movies`.`actor` WHERE `firstName`="actor2"); 