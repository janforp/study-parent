var PROTO_FILE_PATH = '/Users/janita/code/studyCode/sp/grpc_end/proto/Student.proto';
var gRpc = require('grpc');
var gRpcService = gRpc.load(PROTO_FILE_PATH).com.shengsiyuan.netty.proto;

//客户端
var client = new gRpcService.StudentService('localhost:8899',gRpc.credentials.createInsecure());

client.getRealNameByUsername({username:'李四'}, function (error, respData) {
    console.log(respData);
})