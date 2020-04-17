// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Student.proto

package com.shengsiyuan.netty.proto;

/**
 * Protobuf type {@code com.shengsiyuan.netty.proto.StreamResponse}
 */
public final class StreamResponse extends
        com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:com.shengsiyuan.netty.proto.StreamResponse)
        StreamResponseOrBuilder {

  public static final int RESPNSE_INFO_FIELD_NUMBER = 1;

  private static final long serialVersionUID = 0L;

  // @@protoc_insertion_point(class_scope:com.shengsiyuan.netty.proto.StreamResponse)
  private static final StreamResponse DEFAULT_INSTANCE;

  private static final com.google.protobuf.Parser<StreamResponse>
          PARSER = new com.google.protobuf.AbstractParser<StreamResponse>() {
    @Override
    public StreamResponse parsePartialFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
      return new StreamResponse(input, extensionRegistry);
    }
  };

  static {
    DEFAULT_INSTANCE = new StreamResponse();
  }

  private volatile Object respnseInfo_;

  private byte memoizedIsInitialized = -1;

  // Use StreamResponse.newBuilder() to construct.
  private StreamResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }

  private StreamResponse() {
    respnseInfo_ = "";
  }

  private StreamResponse(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
            com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            String s = input.readStringRequireUtf8();

            respnseInfo_ = s;
            break;
          }
          default: {
            if (!parseUnknownField(
                    input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
              e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }

  public static final com.google.protobuf.Descriptors.Descriptor
  getDescriptor() {
    return StudentProto.internal_static_com_shengsiyuan_netty_proto_StreamResponse_descriptor;
  }

  public static StreamResponse parseFrom(
          java.nio.ByteBuffer data)
          throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static StreamResponse parseFrom(
          java.nio.ByteBuffer data,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static StreamResponse parseFrom(
          com.google.protobuf.ByteString data)
          throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static StreamResponse parseFrom(
          com.google.protobuf.ByteString data,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static StreamResponse parseFrom(byte[] data)
          throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static StreamResponse parseFrom(
          byte[] data,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static StreamResponse parseFrom(java.io.InputStream input)
          throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
            .parseWithIOException(PARSER, input);
  }

  public static StreamResponse parseFrom(
          java.io.InputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
            .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static StreamResponse parseDelimitedFrom(java.io.InputStream input)
          throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
            .parseDelimitedWithIOException(PARSER, input);
  }

  public static StreamResponse parseDelimitedFrom(
          java.io.InputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
            .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }

  public static StreamResponse parseFrom(
          com.google.protobuf.CodedInputStream input)
          throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
            .parseWithIOException(PARSER, input);
  }

  public static StreamResponse parseFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
            .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }

  public static Builder newBuilder(StreamResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }

  public static StreamResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  public static com.google.protobuf.Parser<StreamResponse> parser() {
    return PARSER;
  }

  @Override
  @SuppressWarnings({ "unused" })
  protected Object newInstance(
          UnusedPrivateParameter unused) {
    return new StreamResponse();
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }

  @Override
  protected FieldAccessorTable
  internalGetFieldAccessorTable() {
    return StudentProto.internal_static_com_shengsiyuan_netty_proto_StreamResponse_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                    StreamResponse.class, StreamResponse.Builder.class);
  }

  /**
   * <code>string respnse_info = 1;</code>
   *
   * @return The respnseInfo.
   */
  public String getRespnseInfo() {
    Object ref = respnseInfo_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      respnseInfo_ = s;
      return s;
    }
  }

  /**
   * <code>string respnse_info = 1;</code>
   *
   * @return The bytes for respnseInfo.
   */
  public com.google.protobuf.ByteString
  getRespnseInfoBytes() {
    Object ref = respnseInfo_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                      (String) ref);
      respnseInfo_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  @Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) {
      return true;
    }
    if (isInitialized == 0) {
      return false;
    }

    memoizedIsInitialized = 1;
    return true;
  }

  @Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
          throws java.io.IOException {
    if (!getRespnseInfoBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, respnseInfo_);
    }
    unknownFields.writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) {
      return size;
    }

    size = 0;
    if (!getRespnseInfoBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, respnseInfo_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof StreamResponse)) {
      return super.equals(obj);
    }
    StreamResponse other = (StreamResponse) obj;

    if (!getRespnseInfo()
            .equals(other.getRespnseInfo())) {
      return false;
    }
    return unknownFields.equals(other.unknownFields);
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + RESPNSE_INFO_FIELD_NUMBER;
    hash = (53 * hash) + getRespnseInfo().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  @Override
  public Builder newBuilderForType() {
    return newBuilder();
  }

  @Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
            ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
          BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }

  @Override
  public com.google.protobuf.Parser<StreamResponse> getParserForType() {
    return PARSER;
  }

  @Override
  public StreamResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

  /**
   * Protobuf type {@code com.shengsiyuan.netty.proto.StreamResponse}
   */
  public static final class Builder extends
          com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
          // @@protoc_insertion_point(builder_implements:com.shengsiyuan.netty.proto.StreamResponse)
          StreamResponseOrBuilder {

    private Object respnseInfo_ = "";

    // Construct using com.shengsiyuan.netty.proto.StreamResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
            BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }

    public static final com.google.protobuf.Descriptors.Descriptor
    getDescriptor() {
      return StudentProto.internal_static_com_shengsiyuan_netty_proto_StreamResponse_descriptor;
    }

    @Override
    protected FieldAccessorTable
    internalGetFieldAccessorTable() {
      return StudentProto.internal_static_com_shengsiyuan_netty_proto_StreamResponse_fieldAccessorTable
              .ensureFieldAccessorsInitialized(
                      StreamResponse.class, StreamResponse.Builder.class);
    }

    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }

    @Override
    public Builder clear() {
      super.clear();
      respnseInfo_ = "";

      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
    getDescriptorForType() {
      return StudentProto.internal_static_com_shengsiyuan_netty_proto_StreamResponse_descriptor;
    }

    @Override
    public StreamResponse getDefaultInstanceForType() {
      return StreamResponse.getDefaultInstance();
    }

    @Override
    public StreamResponse build() {
      StreamResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public StreamResponse buildPartial() {
      StreamResponse result = new StreamResponse(this);
      result.respnseInfo_ = respnseInfo_;
      onBuilt();
      return result;
    }

    @Override
    public Builder clone() {
      return super.clone();
    }

    @Override
    public Builder setField(
            com.google.protobuf.Descriptors.FieldDescriptor field,
            Object value) {
      return super.setField(field, value);
    }

    @Override
    public Builder clearField(
            com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }

    @Override
    public Builder clearOneof(
            com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }

    @Override
    public Builder setRepeatedField(
            com.google.protobuf.Descriptors.FieldDescriptor field,
            int index, Object value) {
      return super.setRepeatedField(field, index, value);
    }

    @Override
    public Builder addRepeatedField(
            com.google.protobuf.Descriptors.FieldDescriptor field,
            Object value) {
      return super.addRepeatedField(field, value);
    }

    @Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof StreamResponse) {
        return mergeFrom((StreamResponse) other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(StreamResponse other) {
      if (other == StreamResponse.getDefaultInstance()) {
        return this;
      }
      if (!other.getRespnseInfo().isEmpty()) {
        respnseInfo_ = other.respnseInfo_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @Override
    public final boolean isInitialized() {
      return true;
    }

    @Override
    public Builder mergeFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
      StreamResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (StreamResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    /**
     * <code>string respnse_info = 1;</code>
     *
     * @return The respnseInfo.
     */
    public String getRespnseInfo() {
      Object ref = respnseInfo_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
                (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        respnseInfo_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }

    /**
     * <code>string respnse_info = 1;</code>
     *
     * @param value The respnseInfo to set.
     * @return This builder for chaining.
     */
    public Builder setRespnseInfo(
            String value) {
      if (value == null) {
        throw new NullPointerException();
      }

      respnseInfo_ = value;
      onChanged();
      return this;
    }

    /**
     * <code>string respnse_info = 1;</code>
     *
     * @return The bytes for respnseInfo.
     */
    public com.google.protobuf.ByteString
    getRespnseInfoBytes() {
      Object ref = respnseInfo_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
                com.google.protobuf.ByteString.copyFromUtf8(
                        (String) ref);
        respnseInfo_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    /**
     * <code>string respnse_info = 1;</code>
     *
     * @param value The bytes for respnseInfo to set.
     * @return This builder for chaining.
     */
    public Builder setRespnseInfoBytes(
            com.google.protobuf.ByteString value) {
      if (value == null) {
        throw new NullPointerException();
      }
      checkByteStringIsUtf8(value);

      respnseInfo_ = value;
      onChanged();
      return this;
    }

    /**
     * <code>string respnse_info = 1;</code>
     *
     * @return This builder for chaining.
     */
    public Builder clearRespnseInfo() {

      respnseInfo_ = getDefaultInstance().getRespnseInfo();
      onChanged();
      return this;
    }

    @Override
    public final Builder setUnknownFields(
            final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @Override
    public final Builder mergeUnknownFields(
            final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }

    // @@protoc_insertion_point(builder_scope:com.shengsiyuan.netty.proto.StreamResponse)
  }

}

