<template>
  <div>
    <!-- 卡片试图-->
    <el-card>
      <el-table v-loading="loading" :data="userList" style="width: 100%" >
        <el-table-column fixed label="ID" prop="userId" width="150"></el-table-column>
        <el-table-column label="用户名" prop="userName" width="120"></el-table-column>
        <el-table-column label="密码" prop="passWord" width="120"></el-table-column>
        <el-table-column label="手机号" prop="phone" width="120"></el-table-column>
        <el-table-column label="创建时间" prop="createTime" width="120"></el-table-column>
        <el-table-column fixed="right" label="操作" width="120">
        <template #default>
          <el-button type="text" size="small" @click="queryCardById">查看卡包</el-button>
          <el-button type="text" size="small"  @click="deleteUser">删除</el-button>
        </template>
    </el-table-column>
     </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      parm: {
        pageSize:"6",
        pageNum:"1",
        userId:"2a43e3ccf02249718816a4637b867cbb"
        },
      userList: [], // 用户列表
    }
  },
  created() { // 生命周期函数
    this.getUserList()
  },
  methods: {
    async getUserList() {
      const formData = new FormData();
      formData.append('pageNum',this.parm.pageNum);
      formData.append('pageSize',this.parm.pageSize);
      // const { data: res } = await this.$http.get('/user/list')
      const { data: res } = await this.$http.post('/user/page',formData);
      console.log(res)
      // if (res.meta.status !== 200) return this.$message.error(res.meta.msg)
      this.userList = res.items
    },
    async deleteUser() {
      const queryId = new FormData();
      queryId.append('userId', this.parm.userId);
      const { data: res } = await this.$http.post('/user/delete',queryId);
      console.log(res)
    },
    async queryCardById() {
      const { data: res } = await this.$http.get('/user/queryById', {
        userId: this.parm.userId
        });
      console.log(res)
    },
  }
}
</script>