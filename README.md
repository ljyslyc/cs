# cs
eclipse document for management
------
### 实验目的
------
运用Java语言，以迭代方式逐步编程实现一个小型档案管理系统。由此了解软件开发的一般过程，深入理解面向对象语言的基本概念和基本原理，
理解和掌握继承与多态、异常处理、输入输出流、GUI设计、JDBC数据库操作、网络编程、多线程等技术；熟练掌握在Java语言环境下，上述技术的具体实现方法。
------
### 系统功能与描述
-----
1. 实现功能
 *	GUI界面（添加背景框）
 * 用户增删改查功能
 * 	文件上传下载功能
 *	客户端服务端通信
 *	多线程阻塞异步实现。多个客户端并行，并采用同步方法实现
2. 拓展功能
 *	退出系统时服务端客户端响应，及时中断socket连接，结束客户端进程。
 *	基于socket实现客户端上传文件到服务端，从服务端下载文件到客户端。
 *	基于socket通过简易自定义TCP的通信协议实现客户端与数据库分离，用户的增删改查全部由服务端进行。具体过程有：
    * 客户端发送协议头 action表明客户端行为，接着发送可变协议体byte用来进行通信。
    * 服务端接收协议头解析协议头。
    *  服务端通过协议头调用相关方法进行响应
    * 服务端通过简易自定义TCP的通信协议将响应结果反馈回客户端。
 *	背景类的继承，通过BJPanel继承JPanel，重写paint方法，并通过构造函数重载来达到背景图片的自适应大小，以此对背景功能的封装。

