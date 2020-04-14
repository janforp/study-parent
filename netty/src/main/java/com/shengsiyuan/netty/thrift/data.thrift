#生成java类的包
namespace java thrift.generated

#文件包含， include "global.thrift"

typedef i16 short
typedef i32 int
typedef i64 long
typedef bool boolean
typedef string String

# const int DEFAULT = 0

#thrift --gen java src/java/thrift/data.thrift

struct Person {

    1:optional String username,

    2:optional int age,

    3:optional boolean married
}

exception DataException {

    1:optional String message,

    2:optional  String callStack,

    3:optional String date
}

service PersonService {

    Person getPersonByUsername(1:required String username) throws(1:DataException dataException),

    void savePerson(1:required Person person) throws(1:DataException dataException)
}