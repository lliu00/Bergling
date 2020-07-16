<template>
    <div>
        <el-container>
            <my-aside></my-aside>
            <el-main>
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="标题" prop="title">
                        <el-input v-model="ruleForm.title"></el-input>
                    </el-form-item>
                    <el-form-item label="简介" prop="description">
                        <el-input type="textarea" v-model="ruleForm.description"></el-input>
                    </el-form-item>
                    <el-form-item label="内容" prop="content">
                        <mavon-editor v-model="ruleForm.content"></mavon-editor>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">发表</el-button>
                        <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                </el-form>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import Aside from "../components/Aside";

    export default {
        name: "BlogEdit",
        components: {
            'my-aside': Aside
        },
        data() {
            return {
                ruleForm: {
                    title: '',
                    content: '',
                    description: '',
                },
                rules: {
                    title: [
                        { required: true, message: '请输入标题', trigger: 'blur' },
                        { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
                    ],
                    description: [
                        { required: true, message: '请输入简介', trigger: 'blur' }
                    ],
                    content: [
                        { required: true, message: '请输入内容', trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        var that = this;
                        this.$axios.post('/blogs', this.ruleForm).then(resp => {
                            console.log(resp);
                            that.$alert('操作成功', '消息提示', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    that.$router.push('/blogs')
                                }
                            });
                        },err => {
                            console.log(err)
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        },
        created() {
            var blogId = this.$route.params.blogId;
            console.log(blogId);
            var that = this;
            this.$axios.get('/blogs/' + blogId).then(resp => {
                console.log(resp);
                that.ruleForm.title = resp.data.result.blog.title;
                that.ruleForm.description = resp.data.result.blog.description;
                that.ruleForm.content = resp.data.result.blog.content;
            })
        }
    }
</script>

<style scoped>

</style>