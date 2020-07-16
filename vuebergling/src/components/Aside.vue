<template>
    <div class="tac">
        <el-aside width="250px">
            <el-row class="demo-avatar demo-basic">
                    <div class="demo-basic--circle">
                        <div class="block">
                            <el-avatar :size="120" :fit="fit" :src="user.circleUrl"></el-avatar>
                        </div>
                        <p>{{user.name}}</p>
                    </div>

            </el-row>
            <el-row class="tac">
                <el-menu
                        default-active="2"
                        class="el-menu-vertical-demo">
                    <el-menu-item index="1">
                        <i class="el-icon-s-home"></i>
                        <span slot="title">
                            <el-link href="/blogs">home</el-link>
                        </span>
                    </el-menu-item>
                    <el-menu-item index="2">
                        <i class="el-icon-position"></i>
                        <span slot="title">
                            <el-link href="#">archive</el-link>
                        </span>
                    </el-menu-item>
                    <el-menu-item index="3">
                        <i class="el-icon-price-tag"></i>
                        <span slot="title">
                            <el-link href="#">tags</el-link>
                        </span>
                    </el-menu-item>
                    <el-menu-item index="4">
                        <i class="el-icon-position"></i>
                        <span slot="title">
                            <el-link href="#">about</el-link>
                        </span>
                    </el-menu-item>
                    <el-menu-item index="5">
                        <i class="el-icon-edit"></i>
                        <span slot="title">
                            <el-link href="/blogs/add">publish</el-link>
                        </span>
                    </el-menu-item>
                </el-menu>
            </el-row>
            <el-button class="login-button" type="primary" v-show="!hasLogin" plain>
                <el-link href="/login">登录</el-link>
            </el-button>
            <el-button class="quit-button" @click="logout" type="primary" v-show="hasLogin" plain>退出</el-button>
        </el-aside>
    </div>
</template>

<script>
    export default {
        name: "Aside",
        data () {
            return {
                hasLogin: false,
                fit: 'full',
                user: {
                    name: '越今朝',
                    circleUrl: "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594807093521&di=bd119a1f27d13162861371579cfa474c&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201607%2F10%2F20160710170210_GUEVt.jpeg",

                }}
        },
        mounted() {
            if (this.$store.getters.getUserInfo.username) {
                this.hasLogin = true;
                this.user.name = this.$store.getters.getUserInfo.username;
            }
        },
        methods: {
            logout() {
                var that = this;
                this.$axios.post("/logout").then(response => {
                    this.$router.push('/login');
                },
                err => {
                    console.log(err);
                })
            },
        }
    }
</script>

<style scoped>


    .el-aside {
        background-color: #FFFFFF;
        color: #333;
        text-align: center;
        line-height: 30px;
    }

    .sub-title {
        margin: 0 auto;
    }

    .tac {
        background-color: #FFFFFF;
        color: #333;
        text-align: left;
        line-height: 30px;
    }


    .login-button .quit-button {
        margin: 0 auto;
        float: right;
    }
</style>