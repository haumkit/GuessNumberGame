/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

CREATE TABLE `btlltm`.`users` (
  `userId` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `score` INT NOT NULL,
  `win` INT NOT NULL,
  `draw` INT NOT NULL,
  `lose` INT NOT NULL,
  PRIMARY KEY (`userId`));

INSERT INTO `btlltm`.`users` (`username`, `password`, `score`, `win`, `draw`, `lose`) 
VALUES
('hauvu', '123', 1003, 3, 2, 1),      
('anhduc', '123', 1005, 5, 1, 2),     
('nguyenvan', 'mypassword', 1012, 8, 0, 2),   
('tranbao', 'securepass', 999, 2, 1, 2),      
('lehoang', 'letmein', 1002, 3, 2, 1),        
('kiman', 'abc123', 999, 1, 3, 2),            
('dangkhoa', 'password456', 1015, 10, 2, 3),  
('quangminh', 'minh123', 1001, 2, 1, 3),      
('thuylinh', 'linhpass', 1003, 3, 2, 1),      
('hongnhung', 'nhung123', 998, 1, 2, 4);      
