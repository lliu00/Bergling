<template>
    <div>
        <el-container>
            <my-aside></my-aside>
            <el-main>
                <div class="display">
                    <h2>{{blog.title}}</h2>
                    <el-divider></el-divider>
                    <div class="markdown-body" v-html="blog.content"></div>
                    <el-button class="edit-button" v-show="isOwner" @click="edit" type="primary" icon="el-icon-edit" circle></el-button>
                </div>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import Aside from "../components/Aside";
    import 'github-markdown-css/github-markdown.css'

    export default {
        name: "BlogDetail",
        components: {
            'my-aside': Aside
        },
        data() {
            return {
                blog: {
                    id: this.$route.params.blogId,
                    title: '标题',
                    content: '内容'
                }
            }
        },
        methods: {
            edit() {
                this.$router.push('/blogs/' + this.blog.id + '/edit');
            }

        },
        created() {
            const blogId = this.$route.params.blogId;
            var that = this;
            this.$axios.get('/blogs/' + blogId).then(resp => {
                console.log(resp);
                that.blog.title = resp.data.result.blog.title;
                var markdownIt = require("markdown-it");
                var md = new markdownIt();
                var result = md.render(resp.data.result.blog.content);

                that.blog.content = result;
            },err => {
                console.log(err);
            })
        }
    }
</script>

<style scoped>
    .display {
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        margin: 0 auto;
        width: 70%;
        min-height: 700px;
        padding: 20px 20px;
    }

    .display h2 {
        text-align: center;
    }

    .edit-button {
        float: right;
    }
</style>