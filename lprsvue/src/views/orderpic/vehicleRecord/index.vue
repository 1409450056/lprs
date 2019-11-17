<template>
  <div>
    <div style="width:90%;margin:20px auto;">
      <div>
        <div v-for="(item,index) in tableData" :key="index" style="float:left;width:25%">
          <img :src="item.url" style="display:block" width="200" height="200" @click="handclick(index)">
          <span style="display:inline-block;width:200px;text-align:center;color:red;font-weight:bold;">{{ item.number }}</span>
        </div>
      </div>
      <div class="pagination" style="padding-top:500px;padding-left:315px">
        <el-pagination background layout="total, prev, pager, next" :current-page="query.pageIndex" :page-size="query.pageSize" :total="total" @current-change="handlePageChange"></el-pagination>
      </div>
      <el-dialog :visible.sync="dialogVisible2" title="车辆订单详情" width="80%">
        <el-table v-loading="listLoading" :data="list" element-loading-text="Loading" border fit highlight-current-row style="margin-top:30px;">
          <el-table-column align="center" label="订单编号" width="95">
            <template slot-scope="scope">
              {{ scope.row.orderNo }}
            </template>
          </el-table-column>
          <el-table-column label="车牌号" width="110" align="center">
            <template slot-scope="scope">
              {{ scope.row.number }}
            </template>
          </el-table-column>
          <el-table-column label="进入时间" width="150" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.deployTime }}</span>
            </template>
          </el-table-column>
          <el-table-column label="离开时间" width="150" align="center">
            <template slot-scope="scope">
              {{ scope.row.leftTime }}
            </template>
          </el-table-column>
          <el-table-column class-name="status-col" label="状态" width="110" align="center">
            <template slot-scope="scope">
              <el-tag :type="scope.row.status | statusFilter">{{ scope.row.status }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="created_at" label="费用" width="200">
            <template slot-scope="scope">
              <span>{{ scope.row.price }}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" label="操作">
            <template slot-scope="scope">
              <el-button type="danger" size="small" @click="handleDelete(scope)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div style="text-align:right;">
          <el-button type="danger" @click="dialogVisible2=false">返回</el-button>
          <el-button type="primary" @click="updateAllByUrl(indexs)">标记为外卖车辆</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import { selectAllByMarkOrder, updateAllByUrl } from '@/api/orderpic'
import { deleteOrder, selectByNumber } from '@/api/table'
export default {
  data() {
    return {
      indexs: 0,
      list: null,
      total: 0,
      listLoading: true,
      dialogVisible2: false,
      dialogVisible1: false,
      resultData: [],
      query: {
        pageIndex: 1,
        pageSize: 8
      },
      pictures: [{
        number: '粤1',
        url: '/static/img/zfbpay.fdcd38f5.png',
        mark: 0
      }]
    }
  },
  computed: {
    tableData() {
      return this.resultData
        .slice(
          (this.query.pageIndex - 1) * this.query.pageSize,
          this.query.pageIndex * this.query.pageSize
        )
    }
  },
  created() {
    this.getList()
  },
  methods: {

    handclick(index) {
      this.dialogVisible2 = true
      this.selectByNumber(index)
      this.indexs = index
    },
    getList() {
      this.listLoading = true
      // this.resultData = this.pictures
      // this.total = this.pictures.length
      selectAllByMarkOrder().then(response => {
        this.resultData = response.data
        this.total = this.resultData.length
        this.listLoading = false
      })
    },
    // 分页导航
    handlePageChange(val) {
      this.$set(this.query, 'pageIndex', val)
      this.getList()
    },
    selectByNumber(index) {
      this.listLoading = true
      selectByNumber(encodeURI(encodeURI(this.tableData[index].number))).then(response => {
        const listArray = []
        for (const i in response.data) {
          listArray.push(response.data[i])
        }
        this.list = listArray
        this.listLoading = false
      })
      this.listLoading = false
    },
    handleDelete({ $index, row }) {
      this.$confirm('确认删除?', 'Warning', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(async() => {
          row.status === 1 ? (
            await deleteOrder(row.orderNo),
            this.list.splice($index, 1),
            this.$message({
              type: 'success',
              message: '删除成功!'
            })) : (this.$message({
            type: 'warning',
            message: '订单未完成!'
          }))
          this.dialogVisible2 = false
        })
        .catch(err => { console.error(err) })
    },
    updateAllByUrl(indexs) {
      updateAllByUrl(encodeURI(encodeURI(this.tableData[indexs].number)))
      this.$message({
        type: 'success',
        message: '外卖车辆标记成功!'
      })
      this.dialogVisible2 = false
    }
  }
}

</script>
