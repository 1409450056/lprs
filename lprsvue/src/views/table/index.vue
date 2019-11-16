<template>
  <div class="app-container">
    <el-button type="primary" @click="handleAddOrder">添加订单</el-button>
    <div style = "margin-left:50px;display:inline">
      <el-input v-model="order.number" maxlength="7" placeholder="输入车牌号搜索" style="width: 200px;"/>
      <el-button class="filter-item" size="mini" type="success" icon="el-icon-search" @click="selectByNumber">搜索</el-button>
    </div>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
      style = "margin-top:30px;"
    >
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
          <el-button type="primary" size="small" @click="handleFinished(scope)">完成</el-button>
          <el-button type="danger" size="small" @click="handleDelete(scope)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :visible.sync="dialogVisible" :title="dialogType==='edit'?'修改订单':'新订单'">
      <el-form :model="order" label-width="80px" label-position="left">
        <el-form-item label="车牌号">
          <el-input v-model="order.number" maxlength="7" placeholder="车牌号" />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="fetchData">取消</el-button>
        <el-button type="primary" @click="confirmOrder">确认</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="dialogVisible1" title="请支付费用以完成订单">
      <el-form :model="order" label-width="80px" label-position="left">
        <template>
          <div><img src="@/assets/pay_images/zfbpay.png" height="315" width="315"/></div>
        </template>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogVisible1=false">取消</el-button>
        <el-button type="primary" @click="confirmOrder1">确认</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getAllOrders, deleteOrder, updateOrder, addOrder, finishOrder, selectByNumber } from '@/api/table'

const defaultOrder = {
  orderNo: '',
  number: '',
  deployTime: '',
  leftTime: '',
  status: '',
  price: ''
}

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        0: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      order: Object.assign({}, defaultOrder),
      dialogVisible: false,
      dialogVisible1: false,
      checkStrictly: false,
      dialogType: 'new',
      list: null,
      listLoading: true
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      this.dialogVisible = false
      getAllOrders().then(response => {
        this.list = response.data
        this.listLoading = false
      })
    },

    selectByNumber() {
      // selectByOrderNo(this.order.orderNo).then(response => {
      //   this.list = response.data
      //   this.listLoading = false
      //   alert(list)
        
      // })
      this.listLoading = true
      selectByNumber(encodeURI(encodeURI(this.order.number))).then(response => {
        const listArray = []
        for (const i in response.data) {
          listArray.push(response.data[i])
        }
        this.list = listArray
        this.listLoading = false
      })
      this.listLoading = false
      //alert(this.list)
        
        
    },

    handleAddOrder() {
      this.list = Object.assign([], defaultOrder)
      if (this.$refs.tree) {
        this.$refs.tree.setCheckedNodes([])
      }
      this.dialogType = 'new'
      this.dialogVisible = true
    },

    handleFinished(scope) {
      this.dialogVisible1 = true
      this.checkStrictly = true
      this.order.orderNo = scope.row.orderNo
      this.order.price = scope.row.price
      this.order.status = scope.row.status
      this.$nextTick(() => {
        this.checkStrictly = false
      })
    },

    async confirmOrder1() {
      await finishOrder(this.order.orderNo, this.order)
      for (let index = 0; index < this.list.length; index++) {
        if (this.list[index].orderNo === this.order.orderNo) {
          this.list.splice(index, 1, Object.assign({}, this.order))
          break
        }
      }
      const res = await getAllOrders()
      const listArray = []
      for (const i in res.data) {
        listArray.push(res.data[i])
      }
      this.list = listArray

      this.dialogVisible1 = false
      this.$notify({
        title: 'Success',
        dangerouslyUseHTMLString: true,
        message: 'finishOrder',
        type: 'success'
      })
    },

    handleEdit(scope) {
      this.dialogType = 'edit'
      this.dialogVisible = true
      this.checkStrictly = true
      this.orderNo = scope.row.orderNo
      this.order.deployTime = scope.row.deployTime
      this.order.number = scope.row.number
      this.order.leftTime = scope.row.leftTime
      this.order.price = scope.row.price
      this.order.status = scope.row.status
      this.$nextTick(() => {
        // const routes = this.generateRoutes(this.role.routes)
        // this.$refs.tree.setCheckedNodes(this.generateArr(routes))
        // set checked state of a node not affects its father and child nodes
        this.checkStrictly = false
      })
    },

    async confirmOrder() {
      const isEdit = this.dialogType === 'edit'

      // const checkedKeys = this.$refs.tree.getCheckedKeys()
      // this.role.routes = this.generateTree(deepClone(this.serviceRoutes), '/', checkedKeys)

      if (isEdit) {
        await updateOrder(this.order)
        for (let index = 0; index < this.list.length; index++) {
          if (this.list[index].orderNo === this.order.orderNo) {
            this.list.splice(index, 1, Object.assign({}, this.order))
            break
          }
        }
        const res = await getAllOrders()
        const listArray = []
        for (const i in res.data) {
          listArray.push(res.data[i])
        }
        this.list = listArray
      } else {
        if (this.order.number.length > 0) {
          await addOrder(this.order)
          const res = await getAllOrders()
          this.list = res.data
          // this.rolesList.push(this.role)

          const { number } = this.order
          this.dialogVisible = false
          this.$notify({
            title: 'Success',
            dangerouslyUseHTMLString: true,
            message: `
                <div>车牌号: ${number}</div>
              `,
            type: 'success'
          })
        } else {
          this.fetchData()
          this.dialogVisible = false
          this.$notify({
            title: 'Fail',
            dangerouslyUseHTMLString: true,
            message: `
                <div>车牌号不能为空，请确认好重新添加</div>
              `,
            type: 'fail'
          })
        }
      }
    },
    handleDelete({ $index, row }) {
      this.$confirm('确认删除?', 'Warning', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(async() => {
          await deleteOrder(row.orderNo)
          this.list.splice($index, 1)
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this.fetchData()
        })
        .catch(err => { console.error(err) })
    }
  }
}
</script>
