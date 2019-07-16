import Vue from 'vue'
import Router from 'vue-router'
import MyIpShardSettinng from '@/components/MyIpShardSettinng'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/',
      redirect: MyIpShardSettinng,
    },
    {
      path: '/MyIpShardSettinng',
      name: 'MyIpShardSettinng',
      component: MyIpShardSettinng
    }
  ]
})
