<template>
  <div style="width:90%;margin:20px auto;">
    <!-- <el-table :data="tableData" stripe style="width:100%">
      <el-table-column prop="pictures" label="图片订单">
        <template scope="scope"> --> 
          <div>　　　　
          <!-- <img v-for="(item,index) in scope.row.pictures" :src="item" @click="handclick(scope,index)" width="200" height="200"/> -->
          <div v-for="(item,index) in tableData" :key="index" style="float:left;width:25%">
            <img :src="item.url" @click="handclick(index)" width="200" height="200" style="display:block" />
            <span style="display:inline-block;width:200px;text-align:center;color:red;font-weight:bold;">{{ item.number }}</span>
          </div>
        </div>　　
        <!-- </template>
      </el-table-column>
    </el-table> -->
    <div class="pagination" style="padding-top:500px;padding-left:315px">
                <el-pagination
                    background
                    layout="total, prev, pager, next"
                    :current-page="query.pageIndex"
                    :page-size="query.pageSize"
                    :total="total"
                    @current-change="handlePageChange"
                ></el-pagination>
    </div>
  </div>
</template>


<script>
import Pagination from '@/components/Pagination'
import { selectAllByMarkOrder,selectByMark } from '@/api/orderpic'


export default {
  components:{Pagination},
  data() {
    return {
      list:null,
      total:0,
      listLoading:true,
      dialogVisible2: false,
      dialogVisible1: false,
      resultData:[],
      query:{
        pageIndex:1,
        pageSize:8
      },
      pictures: [
        {
          name: '粤1',
          url: '/static/img/zfbpay.fdcd38f5.png'
        }
        ]
      }
  },
  computed: {
    tableData() {
        // 获取一页的数据
      return this.resultData
        .slice(
          (this.query.pageIndex - 1) * this.query.pageSize,
          this.query.pageIndex * this.query.pageSize
      )           //slice(star,end)函数返回从star到end前的函数、不包括end
    }
  },
  created() {
    this.getList()
  },
  methods: {

    handclick(index) {
      this.dialogVisible2 = true
      alert(this.tableData[index].number)
      this.selectByNumber(index)
    },
    getList() {
      this.listLoading = true
      // this.resultData = this.pictures
      // this.total = this.pictures.length
      selectByMark().then(response => {
        this.resultData = response.data
        this.total = this.resultData.length
        this.listLoading = false
      })
    },
    // 分页导航
    handlePageChange(val) {
      this.$set(this.query, 'pageIndex', val)
      this.getList()
    }
  }
}
</script>
