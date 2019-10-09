<template>
  <el-table :data="list" style="width: 100%;padding-top: 15px;">
    <el-table-column label="Order_No" min-width="100">
      <template slot-scope="scope">
        {{ scope.row.orderNo | orderNoFilter }}
      </template>
    </el-table-column>
    <el-table-column label="Plate" width="170" align="center">
      <template slot-scope="scope">
        {{ scope.row.number }}
      </template>
    </el-table-column>
    <el-table-column label="Price" width="170" align="center">
      <template slot-scope="scope">
        {{ scope.row.price | toThousandFilter }}
      </template>
    </el-table-column>
    <el-table-column label="Status" width="100" align="center">
      <template slot-scope="{row}">
        <el-tag :type="row.status | statusFilter">
          {{ row.status }}
        </el-tag>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import { getAllOrders } from '@/api/table'

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        success: 'success',
        pending: 'danger'
      }
      return statusMap[status]
    },
    orderNoFilter(str) {
      return str.substring(0, 30)
    },
    toThousandFilter(num) {
      return (+num || 0).toString().replace(/^-?\d+/g, m => m.replace(/(?=(?!\b)(\d{3})+$)/g, ','))
    }

  },
  data() {
    return {
      list: null
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      getAllOrders().then(response => {
        this.list = response.data
      })
    }
  }
}
</script>
