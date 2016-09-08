**Домашнее задание к модулю 10**
---------------------

Для приложения ресторан добавить REST API, который будет позволять: 
 - получить список всех меню (только названия меню без блюд в них);         Menu        getAll
 - по ID получить меню со списком блюд в этом меню;                         Menu        findMenuByID
 - найти меню по имени;                                                     Menu        findByName
 - получить список всех заказов;                                            Orders      getAll
 - получить список только открытых заказов;                                 Orders      getOpenOrders  
 - получить список только закрытых заказов;                                 Orders      getClosedOrders
 - получить заказ по ID;                                                    Orders      findOrderById
 - получить список всех сотрудников (только имена и фамилии);               Employee    getAllEmployeesNames
 - получить сотрудника по ID;                                               Employee    findEmployeeById
 - найти сотрудника/сотрудников по имени, по фамилии, по имени и фамилии.   Employee    findByName, findByFullName, findBySurname
 
 Данные возращать в любом уобном для вас формате (JSON, XML, и т.д.).