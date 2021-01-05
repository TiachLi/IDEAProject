import request from '@/utils/request'
export default {
    //1 订单列表（条件查询分页）
    //current当前页 limit每页记录数 orderQuery条件对象
    getOrderListPage(current,limit,orderQuery) {
        return request({
            //url: '/eduservice/teacher/pageTeacherCondition/'+current+"/"+limit,
            url: `/eduorder/order/getOrderPageByCondition/${current}/${limit}`,
            method: 'post',
            //orderQuery条件对象，后端使用RequestBody获取数据
            //data表示把对象转换json进行传递到接口里面
            data: orderQuery
          })
    },

}
