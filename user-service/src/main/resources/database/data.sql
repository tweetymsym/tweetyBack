
CREATE CONSTRAINT ON (user:User) ASSERT user.email IS UNIQUE //CREATE CONSTRAINT FOR (user:User) REQUIRE user.email is UNIQUE ;


CREATE (yasser:User {userName: 'Karamiyasser', picture: 'myPic',firstName: 'Yasser', lastName: 'Karami',email: 'y@gmail.com', lastTweet: datetime({
                                                                                                                                           year: 2022, month: 11, day: 11,
                                                                                                                                           hour: 12, minute: 31, second: 14, nanosecond: 645876123,
                                                                                                                                           timezone: 'Europe/Stockholm'
                                                                                                                                         })});
CREATE (maymoun:User {userName: 'maymounGhordo', picture: 'myPic',firstName: 'Maymoun', lastName: 'Ghordo',email: 'm@gmail.com', lastTweet: datetime({
                                                                                                                                           year: 1999, month: 11, day: 11,
                                                                                                                                           hour: 12, minute: 31, second: 14, nanosecond: 645876123,
                                                                                                                                           timezone: 'Europe/Stockholm'
                                                                                                                                         })});


CREATE (soumaya:User {userName: 'soumayasoumaya', picture: 'myPic',firstName: 'soumaya', lastName: 'Fourtasi',email: 's@gmail.com', lastTweet: datetime({
                                                                                                                                           year: 2011, month: 11, day: 11,
                                                                                                                                           hour: 12, minute: 31, second: 14, nanosecond: 645876123,
                                                                                                                                           timezone: 'Europe/Stockholm'
                                                                                                                                         })});
MATCH (yasser:User {email: "y@gmail.com"}),
(soumaya:User {email: "s@gmail.com"})
CREATE (yasser)-[r:FOLLOWS]->(soumaya)
RETURN yasser, r, soumaya


MATCH (yasser:User {email: "y@gmail.com"}),
(maymoun:User {email: "m@gmail.com"})
CREATE (yasser)-[r:FOLLOWS]->(maymoun)
RETURN yasser, r, maymoun ;



MATCH (maymoun:User {email: "m@gmail.com"}),
(yasser:User {email: "y@gmail.com"})
CREATE (maymoun)-[r:FOLLOWS]->(yasser),
RETURN yasser, r, maymoun ;


