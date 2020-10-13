# Algorithm-in-Java - Java数据结构与算法

## 第一章 导言

### 1.1 几个经典的面试题

    > 字符串匹配问题
        ·暴力匹配
        ·KMP算法 - 部分匹配表

    > 汉诺塔问题
        ·分治法

    > 八皇后问题
        ·回溯法

    > 骑士周游问题
        ·DFS
        ·贪心法优化

### 1.2 数据结构和算法的重要性

    1.算法是程序的灵魂，优秀的程序可以在海量数据计算时依然保持高速

    2.一般来讲程序会使用内存计算框架（如Spark）和缓存技术（如Redis）等来优化程序，
      这些程序的核心功能就是算法

    3.实际工作中要支撑高并发量

    4.程序员的门槛也越来越高，面试肯定会考察算法

    5.算法学习也是程序员提升的必经之路

## 第二章 数据结构和算法概述

### 2.1 数据结构和算法的关系

    ·数据结构 - data structure 是一门研究组织数据方式的学科，有了编程语言也就有了数据结构。

    ·数据结构是算法的基础

    ·程序 = 数据结构 + 算法

### 2.2 几个实际编程中遇到的问题

    1. "aabbcc".replace("aa", "dd")

        标准库是怎么做的？

    2.试写出用单链表表示的字符串类及字符串节点类的定义，并依次实现：
        ·构造函数
        ·长度计算
        ·串赋值
        ·判断两串相等
        ..

    3.一个五子棋游戏
        ·如何判断输赢？
        ·如何存档？
        ·如何读档？
        ·如何悔棋？

    4.约瑟夫问题/丢手帕问题
        1~n个人，第m个人出列；继续从1开始，第m个人出列...
        求出出队编号序列

    5.修路问题 - 最小生成树（普利姆算法）

    6.最短路径问题 - 弗洛伊德算法

    7.汉诺塔 - 分治法

    8.八皇后问题 - 回溯法

### 2.3 线性结构和非线性结构

    1.线性结构
        ·线性结构是最常用的数据结构，特点是元素之间存在一对一的关系
        ·线性结构有两种不同的存储方式：
            -顺序存储方式
            -链式存储方式
        ·顺序存储方式的线性表称为顺序表，顺序表中存储的元素是连续的
        ·链式存储的线性表称为链表，链表中元素的地址不一定是连续的
        ·线性结构常见的有：
            -数组
            -队列
            -链表
            -栈

    2.非线性结构
        ·二维数组
        ·多维数组
        ·广义表
        ·树结构
        ·图结构

## 第三章 稀疏数组和队列

### 3.1 五子棋存盘和读取

    . . . . . . . . . .
    . . x . . . . . . .
    . . . o . . . . . .
    . . . . . . . . . .
    . . . . . . . . . .
    . . . . . . . . . .
    . . . . . . . . . .
    . . . . . . . . . .

    ↓

    0 0 0 0 0 0 0 0 0 0
    0 0 1 0 0 0 0 0 0 0
    0 0 0 2 0 0 0 0 0 0
    0 0 0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0 0 0

    ！该二维数组的大部分值都是默认值0，因此直接保存会记录很多没有意义的数据

### 3.2 稀疏数组

    当一个数组中大部分元素是0，或者为同一个值时，可以用稀疏数组来保存该数组。

    处理方法：
        1.记录一共有几行几列，有多少个不同的值
        2.把具有不同值元素的行列及值记录在一个数组中，就可以缩小保存的规模

    原始二维数组：

        0 0 0 0 0
        0 1 0 0 0
        0 0 3 0 0
        0 0 4 0 0
        0 0 0 0 0

        ↓
    
    稀疏数组：

        行 列 值
        5  5  3
        1  1  1
        2  2  3
        3  4  4

    用12个值就完成了5*5的数组的记录

### 3.3 应用实例

    1.使用稀疏数组，可以保存二维数组（棋盘、地图等）
    2.把稀疏数组存盘，可以恢复到原来的二维数组
    3.思路整理

        二维数组转稀疏数组的思路：

        

            1.遍历原始二维数组，得到有效数据的个数sum
            2.根据sum就可以创建稀疏数组sparseArray int[sum+1][3]
            3.将二维数组的有效数据存入稀疏数组即可

        稀疏数组转二维数组的思路：

            1.先读取稀疏数组的第一行，根据第一行的数据创建原始二维数组 chessArr int[row][col]
            2.再读取稀疏数组后几行的数据，并赋值给原始二维数组

    4.代码实现 - SparseArray.java

### 3.4 银行排队

    先进先出，叫号系统

### 3.5 队列介绍

    ·队列是一个有序列表，可以用数组或链表来实现
    ·遵循先入先出原则

### 3.6 数组模拟队列

    1.队列本身是有序列表，若用数组结构来存储队列，则声明maxSize为其最大容量
    2.队列的输入输出是分别从前后端来处理，因此需要两个变量
        -front 前端
        -rear 后端
     来记录前后端下标，front随着数据输出而改变，rear随着数据输入而改变

    3.思路分析
        1.addQueue - 入队操作
            ·队列不为空，尾指针后移， rear + 1
            ·若队列为空rear = maxSize - 1，则入队失败

    4.代码实现

    5.问题：
        单纯增长下标的方式下，数组只能使用一次，利用率低

### 3.7 数组模拟环形队列

    ·和底层数组接触时均取模后在运算即可    

## 第四章 链表

### 4.1 链表（LinkedList）介绍

    ·链表是有序链表
    ·一个节点中包含
        -数据域
        -下个节点的指针
    ·链表的各个节点不一定连续存放
    ·链表分为带头节点链表和不带头节点链表

### 4.2 水浒英雄排行榜管理

    使用带头节点的单向链表管理水浒英雄的排行榜管理

    方式一：直接添加到链表尾部
    方式二：添加到链表中后保持有序

### 4.3 思路分析

    class HeroNode {
        int no;
        String name;
        String nickName;
        HeroNode next;
    }

    头节点：
        ·不存放具体数据
        ·用来表示单链表头

    ※有序链表？奇怪的数据结构

### 4.4 修改链表的节点的内容

### 4.5 删除链表的节点

### 4.6 双链表

    1.单链表的缺点：
        ·只能从一个方向查找
        ·不能实现自我删除

    2.双链表和单链表的不同：
        ·每个节点都多一个指针，指向上一个节点

### 4.7 单向环形链表

    1.约瑟夫（Josephus）问题

        编号为1, 2, 3... n的人围坐一圈，编号为1的人从1开始报数，
        数到m的人出列。下一个人再从1开始，直到所有人都出列。求出队编号序列。

    2.思路分析
        ·不带头节点的链表
        ·先创建第一个节点，让它指向自身
        ·之后每加入一个新节点，就把该节点加入已有的环中

    3.代码实现 - Josephus.java

## 第五章 栈

### 5.1 计算表达式

     7*2*2*5-5+1-5+3-3 怎么计算出结果？

     其他应用场景：
        ·子程序调用时返回地址记录
        ·处理递归调用
        ·表达式的转换
        ·二叉树遍历
        ·深度优先搜索法 - DFS

### 5.2 栈的介绍

    ·栈 - Stack
    ·栈是一个先入后出 FILO 的有序列表
    ·变化的一段称为栈顶（Top），固定的一段称为栈底（Bottom）

### 5.3 数组模拟栈

    1.思路分析
        ·底层存储结构是数组
        ·定义top表示栈顶，初始化为-1
        ·入栈操作时top++，将数据存入栈中
        ·出栈操作时取出结果，top--

    2.代码实现 - JojaStack.java

### 5.4 链表模拟栈

    代码实现 - JojoStack.java

### 5.5 栈实现综合计算器(中缀表达式)

    1.思路分析
        ·使用双栈结构
        ·解决操作符优先级问题：
            当前操作符入栈时和栈顶操作符优先级比较，
            当前操作符优先级高时可以直接入栈；否则要提前结算

                - 相同优先级的运算顺序应该是从左向右为正确，而栈从右向左计算，

                所以优先级相同时也需要当场结算
        ·全部扫描结束后，数2 符1运算后 结果入数栈；符号栈空时数栈只有一个值，就是结果

### 5.6 前缀、中缀、后缀表达式

    1.前缀表达式 - 波兰表达式

        运算符位于操作符之前

        (3+4)*5-6 <=> -*+3456

    2.前缀表达式求值

        从右向左扫描，遇到数字时压栈；
        遇到运算符时出栈2次，进行运算；
        运算结果再次入栈

    3.中缀表达式

        就是人类熟悉的计算表达式

    4.后缀表达式 - 逆波兰表达式

        运算符位于操作符之后

        (3+4)*5-6 <=> 34+5*6-

### 5.7 逆波兰计算器

    1.要求
        ·输入一个逆波兰表达式，使用栈，计算结果
        ·支持小括号和多位数整数

    2.思路分析
    
    3.代码实现 - RPolishRotationCalculator.calculate()

### 5.8 中缀表达式转后缀表达式

    步骤
        ·遇到低或平优先级的运算符时，提前结算，生成 2 + 1 的逆序表达式
        ·左括号直接入符号栈
        ·右括号一直结算知道遇到左小括号

    代码实现 - RPolishRotationCalculator.toRPolishRotation()

## 第六章 递归

### 6.1 迷宫问题

    1 1 1 1 1 1
    1 * 0 0 0 1
    1 1 1 0 0 1
    1 0 0 0 0 1
    1 0 0 1 0 1
    1 1 1 1 1 1

        

    *能否从左上角到达右下角？

### 6.2 递归简单案例

    1.打印问题

    public void test(int n) {
        if (n > 1) {
            test(n - 1);
        }
        System.out.print(n + " ");
    }

    -test(5)输出什么？
    -1 2 3 4 5

    2.阶乘问题

    public int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

### 6.3 递归的应用

    很多问题的解决都会用到递归

    ·8皇后问题，球和篮子问题，汉诺塔问题，迷宫问题...
    ·很多排序算法也会用到递归，快排，归并..

### 6.4 递归的规则

    1.执行一次方法，就会创建一个独立的空间（Java中就是一个栈帧）
    2.方法的局部变量是独立的
    3.递归必须向退出递归的条件逼近否则就是无限递归

### 6.5 解决迷宫问题

    代码实现 - Labyrinth.java

    ？如何寻找最短路径

    - 虽然这次将迷宫定义为了一个二维数组，1表示墙，0表示路，但是迷宫其实是一个无向无权图。
    - 将数据结构变为图就可以使用BFS来找最短路径了。

### 6.6 八皇后问题

    在8x8的棋盘上拜访八个皇后，八个皇后不能相互攻击（横、竖、斜都不同行）。

    代码实现 - EightQueens.java

    ！求全部解，找到解后不返回继续循环，真正的回溯

## 第七章 排序算法

### 7.1 介绍

    排序也称排序算法（Sort Algorithm），是将一组数据，依指定的顺序进行排列的过程。

    分类：
        1.内部排序
            将需要处理的所有数据加载到内部存储器中进行排序

        

        2.外部排序
            数据量过大，无法全部加载到内存中，需要借助外部存储装置进行排序

    常见的排序算法：
        -内部排序-插入排序-直接插入排序
                        -希尔排序
                -选择排序-简单选择排序
                        -堆排序
                -交换排序-冒泡排序
                        -快速排序
                -归并排序
                -基数排序（桶排序）
        -外部排序
    

### 7.2 算法的时间复杂度

    1.事后统计法

    2.事前估计法

    3.时间频度
        一个算法花费的时间与算法中语句的执行次数成正比。
        一个算法中语句的执行次数称为语句频度或时间频度。
        记为T(n)。
    
    4.时间复杂度
        如果有辅助函数f(n)，当n趋近于无穷大时，T(n)/f(n)的极限值为不等于0的常数，
        则称f(n)是T(n)的同数量级函数。
        记作 T(n)=O(f(n))
    
    5.常见的时间复杂度
        ·常数阶 O(1)
        ·对数阶 O(log n)
        ·线性阶 O(n)
        ·线性对数阶 O(nlogn)
        ·平方阶 O(n^2)
        ·立方阶 O(n^3)
        ·k次方阶 O(n^k)
        ·指数阶 O(2^n)

### 7.3 算法的空间复杂度

    定义为算法所消耗的存储空间

    做算法分析时，更多的关注时间复杂度

### 7.4 冒泡排序

    冒泡排序（Bubble Sorting）

        通过对待排序序列从前向后依次比较相邻元素，逆序则交换。

        优化：如果一趟比较中没有发生交换，则数组有序

            -> 实测这优化非常鸡肋，1000个随机数的比较次数：
                499500 => 497289
    
    代码实现 - Bubble.java

    -- 100 Random Numbers

        [Bubble.sort] compared: 4950
        [Bubble.sort] swaped  : 2689

### 7.5 选择排序

    选择排序（Select Sorting）

        每次都从整个待排数组中找到最小或最大值，并将其交换到合适的位置

        ·交换次数恒定
    
    代码实现 - Select.java

    -- 100 Random Numbers

        [Select.sort] compared: 4950
        [Select.sort] swaped  : 100

### 7.6 插入排序

    插入排序（Insertion Sorting）

        顺序读取元素，构建有序数组

        ·自带比较剪枝
    
    代码实现 - Insertion.java

    -- 100 Random Numbers

        [Insertion.sort] compared: 2783
        [Insertion.sort] swaped  : 2689

### 7.7 希尔排序

    希尔排序（Shell Sorting）

        简单插入排序经过改进之后的一个高效版本，也称为缩小增量排序

        把记录按照下标的一定增量分组，对每组使用直接插入算法排序；
        随着增量逐渐减少，每组包含的对象越来越多；
        当增量减至1时，排序就结束了。

    代码实现 - Shell.java

    -- 100 Random Numbers

    来自<算法4>：
    步长采取：1,4,13,40... (3n+1)

        [Shell.sort] compared: 742
        [Shell.sort] swaped  : 446

    试试别的：
    步长采取：1,3,7,15,31... (2n+1)

        [Shell.sort] compared: 770
        [Shell.sort] swaped  : 337
    
    步长采取：1,5,21,85... (4n+1)

        [Shell.sort] compared: 780
        [Shell.sort] swaped  : 528

    单纯对半：

        [Shell.sort] compared: 893
        [Shell.sort] swaped  : 450

### 7.8 快速排序

    快速排序（QuickSort）

        是对冒泡排序的一种改进。

        通过一趟排序将数据分割成独立的两部分，其中一部分比另外一部分所有数据都小。
        然后再以此方法对每部分递归处理。
        每部分只剩1个元素时，排序就结束了。

    -- 100 Random Numbers

        [Quick.sort] compared: 635
        [Quick.sort] swaped  : 382

    来自<算法4>：
    分割时左右指针各自尽量连续移动：

        [Quick.sort] compared: 689
        [Quick.sort] swaped  : 173

### 7.9 归并排序

    归并排序（Merge Sort）

        利用归并的思想实现的排序方法，采用经典的分治策略。

    -- 100 Random Numbers

        [Merge.sort] compared: 536
        [Merge.sort] copied  : 1344 => Notice there is no 'swap' in merge sort

### 7.10 基数排序

    基数排序（Radix Sort），又称桶排序（Bucket Sort）

        将所有待比较数字统一为同样长度，然后从低位开始依次进行排序
    
    代码实现 - Bucket.java

    -- 100 Random Numbers

        [Bucket.sort] compared: 0
        [Bucket.sort] copied  : 600 => 100*2*3 (scale: 10^3)

        No compare, no swap ~ 小字母表短键桶排序太强啦 ~

### 7.11 常用排序算法时间复杂度比较

|排序算法|平均|最好|最坏|空间|排序方式|稳定|
|---|---|---|---|---|---|---|---|
|冒泡|O(n^2)|O(n)|O(n^2)|O(1)|In-place|稳定|
|选择|O(n^2)|O(n^2)|O(n^2)|O(1)|In-place|不稳定|
|插入|O(n^2)|O(n)|O(n^2)|O(1)|In-place|稳定|
|希尔|O(nlgn)|？|？|O(1)|In-place|不稳定|
|归并|O(nlgn)|O(nlgn)|O(nlgn)|O(n)|Out-place|稳定|
|快速|O(nlgn)|O(nlgn)|O(n^2)|O(lgn)|In-place|不稳定|
|堆|O(nlgn)|O(nlgn)|O(nlgn)|O(1)|In-place|不稳定|
|桶|O(nk)|O(nk)|O(nk)|O(n^2)|Out-place|稳定|

## 第八章 查找

### 8.1 线性查找

    线性查找（Seq Search）

        序列可以有序也可以无序，依次探查即可。
    
    代码实现 - SeqSearch.java

    -- Look for 20 Random numbers in 100 Random numbers 

        [SeqSearch.search] compared: 1835

### 8.2 二分查找

    二分查找（Binary Search）

        序列必须有序。
        无序数组使用二分查找必须先排序。

    代码实现 - BinarySearch.java

    -- Look for 20 Random numbers in 100 Random numbers 

        [Binary.search] compared: 133

    Extra Cost:

        [Shell.sort] compared: 742
        [Shell.sort] swaped  : 446

### 8.3 差值查找

    类似于二分查找，只是下一个mid值算出时，估算目标和可能出现位置的偏移量

        mid = (low + high)/2
        ↓
        mid = low + (key - a[low])/(a[high] - a[low])*(high - low)

    数组均匀分布的时候可以减少查找次数

    代码实现 - DivisionSearch.java

    -- Look for 20 Random numbers in 100 Random numbers 

        [Division.search] compared: 164

        => 因为下标计算和减法会算出负数，所以每次都要额外判断target是否还在区间内

    Extra Cost:

        [Shell.sort] compared: 742
        [Shell.sort] swaped  : 446

### 8.4 斐波那契查找

    斐波那契查找，又称为黄金分割查找法。

    斐波那契数列1,1,2,3,5,8...两个相邻数的比例逐渐接近黄金分割值0.618

    和差值查找类似，只是mid采用斐波那契数列算出

    mid = low + F(k-1) - 1

    需要提前将序列增长到下一个菲波那切数-1的长度

    1 2 3 4 5 6 7 
    1 2 3 4 ↑ 1 2 
            mid

    代码实现 - Fibonacchi.java

    -- Look for 20 Random numbers in 100 Random numbers 

        [Fibnoacchi.search] compared: 147
    

    Extra Cost:

        [Shell.sort] compared: 742
        [Shell.sort] swaped  : 446

        == not very interesting 唯一的优势是算mid时将除法降级为加减法

## 第九章 哈希表

### 9.1 实际案例

    将员工信息加入如管理系统；当输入id时，查询到员工信息。
        id,gender,age,address...
    
    要求不使用数据库，而且速度越快越好。

### 9.2 哈希表的基本介绍

    哈希表(Hash table)是根据关键码值而直接进行访问的数据结构。
    它通过把关键码值映射到表中的一个位置来访问记录，以加快查找速度。

### 9.3 实现

    要求：
        ·员工只有id和name
        ·用链表保存元素
        ·链表不带表头

## 第十章 树

### 10.1 为什么需要树

    ·数组存储方式
        优点：
            ·通过下标访问速度快
            ·有序的话还可以二分查找
        缺点：
            ·插入删除时需要整体移动，效率低

    ·链表存储方式
        优点：
            ·插入删除时只要维护指针，效率高
        缺点：
            ·访问需要遍历，效率低

        

    ·树
        访问和维护速度都不太差，一种优秀的折中方案
    

### 10.2 二叉树

    树的常用术语：

        ·节点
        ·根节点
        ·父节点
        ·子节点
        ·叶子节点
        ·节点的权/节点的值
        ·路径
        ·层
        ·子树
        ·树的高度
        ·森林

    二叉树的特点：

        1.树有很多种，每个节点最多只能有有两个子节点的一种形式称为二叉树
        2.二叉树的子节点分别称为左子节点和右子节点
        3.如果该二叉树的所有叶子节点都在最后一层，同时节点总数是2^n-1，
        则该二叉树为满二叉树
        4.如果该二叉树所有叶子节点都在最后一层或者倒数第二层，
        而且最后一层的叶子节点在左边连续，倒数第二层的叶子节点在右边连续，
        则该二叉树为完全二叉树
    

### 10.3 遍历二叉树

    1.前序遍历 preOrder

        先输出父节点，再遍历左子树和右子树

    2.中序遍历 inOrder

        先遍历左子树，再输出父节点，最后遍历右子树
    
    3.后序遍历 postOrder

        先遍历左子树，再遍历后子树，最后输出父节点

    代码实现 - TreeNode.preOrder(), TreeNode.inOrder(), TreeNode.postOrder()

### 10.4 查找二叉树

    查找节点值为指定值的节点

        ·前序查找
        ·中序查找
        ·后序查找

    代码实现 - TreeNode.preSearch(int), TreeNode.inSearch(int), TreeNode.postSearch(int)

### 10.5 删除二叉树节点

    若该节点为叶子节点，则直接删除；
    若该节点为非叶子节点，则删除该子树。 - ？？？

    ~无序二叉树删除节点含义模糊~

### 10.6 顺序存储二叉树

    从数据存储的观点来看，数组和树可以相互转换。

          1
      2       3
    4   5   6   7

    [1,2,3,4,5,6,7]

    要求在遍历数组时，仍然可以以前中后序遍历的方式完成

    顺序存储二叉树的特点：

        ·顺序二叉树通常只考虑完全二叉树
        ·第n个元素的左子节点为2*n+1
        ·第n个元素的右子节点为2*n+2
        ·第n个元素的父节点为(n-1)/2

        ※n代表元素的数组下标
    
    代码实现 - BinaryTreeInArray.java

### 10.7 线索化二叉树

    1.n个节点的二叉链表中含有n+1个空指针域。利用二叉链表中的空指针域，
    存放指向该节点在某种遍历次序下的前驱和后继节点的指针。
    2.这种加上了线索的二叉链表称为线索链表，相应的二叉树称为线索二叉树。
    根据线索顺序的不同分为前中后序线索化二叉树。
    3.前一个节点称为前驱节点，后一个节点称为后继节点。

    举例中序线索化

        ·线索化二叉树完成后，left指向的可能是左子树，也可能是前驱节点；
        right指向的可能是右子树，也可能是后继节点。

        ~so不完善的数据结构~

## 第十一章 树结构实际应用

### 11.1 堆排序

    1.基本介绍

        ·利用“堆”这种数据结构设计的一种排序算法，算是选择排序的一种。
        最好最坏时间复杂度都是nlog(n)，不稳定。
        ·堆是具有以下性质的完全二叉树：
            每个节点都大于其左右孩子节点（大顶堆），或者小于其左右孩子节点（小顶堆）。
            不要求孩子节点之间的大小关系。

            大顶堆特点：
                arr[i]>=arr[2i+1] && arr[i]>=arr[2i+2]
                
            ※小顶堆变为均小于等于即可
        ·一般升序采用大顶堆，降序采用小顶堆
    
    2.基本思想

        -将待排序序列构造成一个大顶堆（数组形式即可）
        -构造完成后整个序列的最大值就是堆顶的根节点
        -将其与末尾元素进行交换，此时末尾就是最大值，堆大小减少一
        -重新维持数组为一个堆
        -持续进行至堆大小为1，排序就完成了
    
    3.代码实现 - MaxHeap.java

    -- 100 Random Numbers

        [Heap.sort] compared: 1072
        [Heap.sort] swaped  : 536

        算法4的小技巧：底层数组从位置1开始保存元素，下标容易计算一些

### 11.2 霍夫曼树

    1.基本介绍

        给定n个权值作为n个叶子节点，构造一颗二叉树，若该树的带权路径长度（wpl）
        达到最小，称这样的二叉树为最优二叉树，也称为霍夫曼树（Huffman Tree）。

        霍夫曼树是带权路径长度最短的树，权值较大的节点离根较近。
    
    2.几个重要概念和举例说明
        ·路径和路径长度：
            在一棵树中，从一个节点往下可以达到的孩子或孙子节点之间的通路，
            称为路径。通路中分支的数目称为路径长度。若规定根节点的层数为1，
            则从根节点到第L层节点的路径长度为L-1
        ·节点的权及带权路径长度：
            若将树的节点赋给一个有着某种含义的数值，则这个数值称为该节点的权。
            节点的带权路径长度为：从根节点到该节点之间的路径长度与该节点的
            权的乘积。
        ·树的带权路径长度：
            树的带权路径长度规定为所有叶子节点的带权路径长度之和，
            记为WPL（weighted path length）。
        ·WPL最小的就是霍夫曼树
    
    3.霍夫曼树创建思路
        给定数列，要求转换成一颗霍夫曼树。

        -从小到大进行排序，每个数据都是一个节点，每个节点都可以看做一颗简单的二叉树
        -取出根节点权值最小的两颗二叉树
        -组成一颗新的二叉树，该新二叉树的根节点的权值就是前面两颗二叉树根节点权值的和
        -再将这颗新的二叉树加入数列，重复上述过程直到只剩下一个二叉树，就是霍夫曼树了
    
    4.代码实现 - HuffmanTree.java

        ※这东西的toString没有什么好的方案吗

### 11.3 霍夫曼编码

    1.基本介绍

        ·是一种编码方式，属于一种程序算法
        ·霍夫曼编码是霍夫曼树在电讯通信中的经典应用之一
        ·霍夫曼编码可以广泛地应用于数据文件压缩。压缩率通常在20%到90%之间
        ·霍夫曼编码是可变字长编码（VLC）的一种
    

    2. 原理剖析

        ·定长编码和变长编码
        ·变长编码方式中，字符的编码不能是其他字符编码的前缀，符合此要求的编码叫做前缀编码
        ·霍夫曼编码-按照字符出现频率构建霍夫曼树

        举例：
            i like like like java do you like a java
        统计各字符出现次数：
            d - 1, y - 1, u - 1, j - 2, v - 2, o - 2, l - 4, 
            k - 4, e - 4, i - 5, a - 5, ' ' - 9
        次数作为权值构建霍夫曼树，只有叶子节点有具体的字符值:
            40
            0-----------1
            17          23
            0---1       0-------1
            8   ' ':9   10      13
            0---1       0---1   0---1
            4   l:4     5   i:5 a:5 8
            0---1       0---1       0---1
            j:2 v:2     o:2 3       k:4 e:4
                            0---1
                            u:1 2
                                0---1
                                d:1 y:1
        左枝标记为0，右枝标记为1，各叶子节点经过的路径就是各字符的编码
            d:100110 y:100111 u:10010 j:0000 v:0001 o:1000 l:001
            k:1110 e:1111 i:101 a:110 ' ':01

        

        注意：存在权值相同的字符时，可能构建出不同的霍夫曼树，对应的霍夫曼编码就会不一样
    

    3. 数据压缩案例

        代码实现 - HuffmanTreeEncode.java

        (1)解码的时候必须记得霍夫曼树
        (2)用Java如何真的把编码转换为比特串呢？
    
    4.文件压缩案例

        目标：对一张图片进行无损压缩

        思路：读取一张图片 -> 得到霍夫曼树 -> 进行压缩

        代码实现 - HuffmanZipFile.java

        ※就没有把Huffman编码也一并写进去，一个方法先写后读完事了
        ※然而中途经过的长字符串真是难受

### 11.4 二叉排序树（BST）

    1.先看一个需求

        给一个数列，要求可以快速地查找和添加删除元素
        继续之前数组存储和链表存储的话题

    2.二叉排序树介绍

        BST（Binary Search Tree），对于二叉排序树的任何一个非叶子节点，
        要求左子节点的值比当前值小，右子节点的值比当前值大。
        可以暂时不考虑键值重复的情况

        比如对于一组数 7,3,10,12,5,1,9，可以构造出二叉排序树

            7
            3   10
            1 5 9 12
    
    3.二叉排序树的创建和遍历

    4.二叉排序树节点的删除

        1.删除叶子节点
        2.删除只有一颗子树的节点
        3.删除有两颗子树的节点

        ==> 情况分析也可以，但是很不优雅

    代码实现 - JojaBST.java

        来自《算法4》
            ·由于不额外保存根节点，所以put, delete方法均设计为返回自身的模式
            ·delete方法从deleteMin导出
            ·deleteMin简介：
                -若左子树为空，则自身即为最小节点，删除最小值后剩下右子树
                -若左子树不为空，则获取左子树删除最小节点后的结果，并设为自身的左子树
            ·delete简介：
                -确认自身为删除对象时
                    -若左右子树任意不存在，则另一子树即为删除后结果
                    -若左右子树均存在，则提取右子树最小值为自身根节点
                        -找出右子树最小根节点作为新根节点
                        -删除右子树最小根节点
                        -将当前的左右子树分别赋给新根节点
                        -返回新根节点

                        ↓ 利用临时变量优化一步 ↓

                        -保存自身为临时变量
                        -找出右子树最小根节点作为新根节点
                        -新根节点的右节点赋值为自身右节点删除最小值结果
                        -赋左节点值
                        -返回新根节点
                

### 11.5 平衡二叉树（AVL树）

    二叉排序树可能存在的问题：

        顺序构造时会构造成一个单链表
    
