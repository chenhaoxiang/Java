全站压缩

全站压缩，最大的好久就是帮客户端节省流量。
数据压缩，我们需要用到二个Java类，也就是java.util.zip 中的
类 GZIPOutputStream
此类为使用 GZIP 文件格式写入压缩数据实现流过滤器。 

java.io 
类 ByteArrayOutputStream
此类实现了一个输出流，其中的数据被写入一个 byte 数组。缓冲区会随着数据的不断写入而自动增长。可使用 toByteArray() 和 toString() 获取数据。 


我们利用GZIPOutputStream(OutputStream out) 使用默认缓冲区大小创建新的输出流。
再用write(byte[] b)将 b.length 个字节写入此输出流。 
也就是把数据压缩后写入ByteArrayOutputStream。
