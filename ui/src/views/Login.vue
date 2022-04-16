
<template>
  <!-- <div class="login h-screen w-screen flex justify-center items-center bg-gray-100">
    <div class="login-wrapper w-full sm:w-80 p-4 bg-white shadow-md rounded"> -->
      <!-- <div class="title font-bold text-lg border-b border-gray-200 pb-1 mb-2">系统登录</div> -->
      <el-card>
        <template #header>
          <span style="center">
           <h2 style="color:#cbe9bf;text-align:center">登录</h2>
          </span>
        </template>
      <div class="p-2">
        <el-form size="small" >
          <el-form-item prop="username">
            <el-input placeholder="用户名" v-model:model-value="loginForm.userName" class="w-50 m-2" size="large"/>
          </el-form-item>
          <el-form-item prop="password">
            <el-input show-password placeholder="密码" type="password" v-model:model-value="loginForm.passWord " size="large" />
          </el-form-item>
          <el-form-item>
            <!--
<button type="submit"></button>
-->
            <el-button class="w-full" type="primary" @click="login">登录</el-button>
            <el-button class="w-full" type="primary" @click="register">注册</el-button>
          </el-form-item>
        </el-form>
      </div>
      </el-card>
    <!-- </div>
  </div> -->
</template>

<script >
  export default {
    name: 'Login',
    data () {
      return {
        loginForm: {
          cards: [
                {
                cardId: "",
                cardName: "",
                cardNum: 0,
                createTime: "",
                del: 0,
                userId: ""
                }
            ],
            createTime: "",
            del: 0,
            passWord: "",
            phone: "",
            userId: "",
            userName: ""
        },
        responseResult: []
      }
    },
    methods: {
      login () {
        this.$http
          .post('/login', {
            userName: this.loginForm.userName,
            passWord: this.loginForm.passWord
          })
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              this.$message.success(successResponse.data.msg)
              this.$router.replace({path: '/management'})
            } else {
              this.$message.warning(successResponse.data.msg)
            }
          })
          
          .catch(failResponse => {
          })
        },
        register () {
          this.$router.replace({path: '/management'})
      }
      
    }
  }
</script>


