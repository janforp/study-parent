var PROTO_FILE_PATH = '/Users/janita/code/studyCode/sp/grpc_end/proto/Student.proto';
var gRpc = require('grpc');
var gRpcService = gRpc.load(PROTO_FILE_PATH).com.shengsiyuan.netty.proto;

//服务端
var server = new gRpc.Server();

server.addService(gRpcService.StudentService.service, {
    getRealNameByUsername: getRealNameByUsername
});

server.bind('localhost:8899', gRpc.ServerCredentials.createInsecure());
server.start();

function getRealNameByUsername(call, callback) {
    console.log("call: " + call.request.username);
    callback(null, {realName: "node服务端返回的张三"})
}