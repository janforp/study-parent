// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Student.proto

package com.shengsiyuan.netty.proto;

public final class StudentProto {
  static final com.google.protobuf.Descriptors.Descriptor
          internal_static_com_shengsiyuan_netty_proto_MyRequest_descriptor;

  static final
  com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internal_static_com_shengsiyuan_netty_proto_MyRequest_fieldAccessorTable;

  static final com.google.protobuf.Descriptors.Descriptor
          internal_static_com_shengsiyuan_netty_proto_MyResponse_descriptor;

  static final
  com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internal_static_com_shengsiyuan_netty_proto_MyResponse_fieldAccessorTable;

  static final com.google.protobuf.Descriptors.Descriptor
          internal_static_com_shengsiyuan_netty_proto_StudentRequest_descriptor;

  static final
  com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internal_static_com_shengsiyuan_netty_proto_StudentRequest_fieldAccessorTable;

  static final com.google.protobuf.Descriptors.Descriptor
          internal_static_com_shengsiyuan_netty_proto_StudentResponse_descriptor;

  static final
  com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internal_static_com_shengsiyuan_netty_proto_StudentResponse_fieldAccessorTable;

  static final com.google.protobuf.Descriptors.Descriptor
          internal_static_com_shengsiyuan_netty_proto_StuentResponseList_descriptor;

  static final
  com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internal_static_com_shengsiyuan_netty_proto_StuentResponseList_fieldAccessorTable;

  private static com.google.protobuf.Descriptors.FileDescriptor
          descriptor;

  static {
    String[] descriptorData = {
            "\n\rStudent.proto\022\033com.shengsiyuan.netty.p" +
                    "roto\"\035\n\tMyRequest\022\020\n\010username\030\001 \001(\t\"\036\n\nM" +
                    "yResponse\022\020\n\010realname\030\002 \001(\t\"\035\n\016StudentRe" +
                    "quest\022\013\n\003age\030\001 \001(\005\":\n\017StudentResponse\022\014\n" +
                    "\004name\030\001 \001(\t\022\013\n\003age\030\002 \001(\005\022\014\n\004city\030\003 \001(\t\"[" +
                    "\n\022StuentResponseList\022E\n\017studentResponse\030" +
                    "\001 \003(\0132,.com.shengsiyuan.netty.proto.Stud" +
                    "entResponse2\354\002\n\016StudentService\022j\n\025GetRea" +
                    "lNameByUsername\022&.com.shengsiyuan.netty." +
                    "proto.MyRequest\032'.com.shengsiyuan.netty." +
                    "proto.MyResponse\"\000\022q\n\020GetStudentsByAge\022+" +
                    ".com.shengsiyuan.netty.proto.StudentRequ" +
                    "est\032,.com.shengsiyuan.netty.proto.Studen" +
                    "tResponse\"\0000\001\022{\n\027GetStudentWrapperByAges" +
                    "\022+.com.shengsiyuan.netty.proto.StudentRe" +
                    "quest\032/.com.shengsiyuan.netty.proto.Stue" +
                    "ntResponseList\"\000(\001B-\n\033com.shengsiyuan.ne" +
                    "tty.protoB\014StudentProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
            .internalBuildGeneratedFileFrom(descriptorData,
                    new com.google.protobuf.Descriptors.FileDescriptor[] {
                    });
    internal_static_com_shengsiyuan_netty_proto_MyRequest_descriptor =
            getDescriptor().getMessageTypes().get(0);
    internal_static_com_shengsiyuan_netty_proto_MyRequest_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_com_shengsiyuan_netty_proto_MyRequest_descriptor,
            new String[] { "Username", });
    internal_static_com_shengsiyuan_netty_proto_MyResponse_descriptor =
            getDescriptor().getMessageTypes().get(1);
    internal_static_com_shengsiyuan_netty_proto_MyResponse_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_com_shengsiyuan_netty_proto_MyResponse_descriptor,
            new String[] { "Realname", });
    internal_static_com_shengsiyuan_netty_proto_StudentRequest_descriptor =
            getDescriptor().getMessageTypes().get(2);
    internal_static_com_shengsiyuan_netty_proto_StudentRequest_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_com_shengsiyuan_netty_proto_StudentRequest_descriptor,
            new String[] { "Age", });
    internal_static_com_shengsiyuan_netty_proto_StudentResponse_descriptor =
            getDescriptor().getMessageTypes().get(3);
    internal_static_com_shengsiyuan_netty_proto_StudentResponse_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_com_shengsiyuan_netty_proto_StudentResponse_descriptor,
            new String[] { "Name", "Age", "City", });
    internal_static_com_shengsiyuan_netty_proto_StuentResponseList_descriptor =
            getDescriptor().getMessageTypes().get(4);
    internal_static_com_shengsiyuan_netty_proto_StuentResponseList_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_com_shengsiyuan_netty_proto_StuentResponseList_descriptor,
            new String[] { "StudentResponse", });
  }

  private StudentProto() {
  }

  public static void registerAllExtensions(
          com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
          com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
            (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  public static com.google.protobuf.Descriptors.FileDescriptor
  getDescriptor() {
    return descriptor;
  }

  // @@protoc_insertion_point(outer_class_scope)
}
