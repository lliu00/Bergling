<template>
    <div>
        <el-container>
            <my-aside></my-aside>
            <el-main>
                <div class="block">
                    <el-timeline >
                        <el-timeline-item :timestamp="blog.createdAt" placement="top" v-for="blog in blogs">
                            <el-card>
                                <h4>
                                    <router-link :to="{name: 'blogDetail', params: {blogId: blog.id}}">
                                        {{blog.title}}
                                    </router-link>
                                </h4>
                                <p>{{blog.description}} {{blog.createdAat}}</p>
                            </el-card>
                        </el-timeline-item>
                    </el-timeline>
                    <div class="pagination">
                        <el-pagination
                                layout="prev, pager, next"
                                :page-size="pageSize"
                                :current-page="pageNum"
                                :total="total"
                                @current-change="list">
                        </el-pagination>
                    </div>
                </div>
            </el-main>
        </el-container>

    </div>
</template>

<script>
    import Aside from "../components/Aside";

    export default {
        name: "Blogs",
        components: {
            'my-aside': Aside
        },
        data() {
            return {
                blogs: {},
                pageNum: 1,
                pageSize: 4,
                total: 0,
                pages: 0
            }
        },
        methods: {
            list(page) {
                var that = this;
                this.$axios.get('/blogs?page=' + page).then(resp => {
                    console.log(resp)
                    that.blogs = resp.data.result.pageInfo.list;
                    that.pageNum = resp.data.result.pageInfo.pageNum;
                    that.pageSize = resp.data.result.pageInfo.pageSize;
                    that.total = resp.data.result.pageInfo.total;
                    that.pages = resp.data.result.pageInfo.pages;
                },
                err => {
                    console.log(err)
                })
            }
        },
        created() {
            this.list(1);
        }
    }
</script>

<style scoped>

    .pagination {
        text-align: center;
    }

</style>