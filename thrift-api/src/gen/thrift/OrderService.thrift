namespace java com.yihua.thrift.api

struct Order {
    1:i64 orderId,
    2:i64 userId,
    3:string title,
    4:string url,
    5:i64 tradeTime,
}

service OrderService{
    Order getById(1:i64 orderId)
    list<Order> getByUserId(1:i64 userId)
}