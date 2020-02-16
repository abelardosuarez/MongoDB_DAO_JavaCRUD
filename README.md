# MongoDB_DAO_JavaCRUD  Author Abelardo Suarez Prato

DAO for Java objects to MongoDB CRUD.
The Teacher class implements interface ITeacher that extends interfaces IId,IPerson.
It's very important that the object include in collection implements interface.
Similar to SQL DAO.

You can use local or remote host.
You can open a free account in https://www.mongodb.com/cloud/atlas). Uses your user,password and server cloud/atlas.

Example 
MongoDAO collection = new MongoDAO("mongodb+srv://user:password@cluster0-p4lmg.mongodb.net/","db","collection");

Execute in eclipse the class Main_MongoTeachers.
The output in console is similar to RunView file.

different cases are tested
(00) Drop collection in MongoDB Server if exits
(01) Insert object without id / return true
(02) Print collection / view JSON collection by console
(03) Get collection / return all objects in collection
(04) Update attribute name from particular object id. / return true
(05) Update object not in MongoDB. / return false
(06) Insert object with new id / Return true
(07) Update without change / Return false
(08) Insert object with old id (exist in MongoDB) / Return false (Mongo Fatal error = E11000 duplicate key error collection: faculty.teachers)
(09) Delete object (in MongoDB) / return true = Ok teacher
(10) Delete object (not in MongoDB) / return false = Error Teacher
(11) GetById objectId / return object

Eclipse JRESystem Library[javaSE-1.8]

Including in Library folder.
bson-3.8.2.jar
gson-2.2.2.jar
mongodb-driver-core-3.11.0.jar
mongo-java-driver-3.12.1.jar

include in Teachers package the classes:
Id, Main_MongoTeachers, MongoDAO,Person and Teacher
and the interfaces: IId, IPerson and ITeacher.


