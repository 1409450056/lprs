<template>
  <div class="block">
    <el-timeline>
      <el-timeline-item v-for="(item,index) of timeline" :key="index" :timestamp="item.timestamp" placement="top">
        <el-card>
          <h4>{{ item.title }}</h4>
          <p>{{ item.content }}</p>
        </el-card>
      </el-timeline-item>
    </el-timeline>
  </div>
</template>

<script>
export default {
  data() {
    return {
      timeline: [
        {
          timestamp: '2019/10/26',
          title: 'hzh分支创建',
          content: 'nieniechange committed 2019/4/20 20:46'
        },
        {
          timestamp: '2019/10/1',
          title: '首次部署在服务器',
          content: 'zhou997 producted 2019/4/21 20:46'
        },
        {
          timestamp: '2019/9/22',
          title: 'czj分支创建',
          content: 'LOVE1—9 committed 2019/4/22 20:46'
        },
        {
          timestamp: '2019/7/8',
          title: '启动项目仓库',
          content: 'zhou997 committed 2019/7/8 20:46 '
        }
      ]
    }
  }
}
</script>
