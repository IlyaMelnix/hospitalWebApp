/*  Добавление пользователя
    private static final String INSERT_NEW_USER = "INSERT INTO `hospital`.`user` (`username`, `password`, `name`, `surname`, `patronymic`, `status`, `diagnosis`) VALUES (?, ?, ?, ?, ?, ?, ?);";
*/
INSERT INTO `hospital`.`user` (`username`, `password`, `name`, `surname`, `patronymic`, `status`, `diagnosis`) VALUES ('test', 'test', 'name', 'surname', 'patronymic', '1', 'Здоров');

/* Удаление пользователя */
DELETE FROM `hospital`.`user` WHERE `iduser`='1000';