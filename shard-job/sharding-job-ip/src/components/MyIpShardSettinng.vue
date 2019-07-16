<template>
  <div>
    <!-- 列表  START-->
    <div v-if="showA">
      <el-select label="执行器" @change="mySelect()" v-model="value" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        ></el-option>
      </el-select>
      <el-button type="primary" @click="chengShowValue()">添加</el-button>
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="shard_name" label="分片名称" width="180"></el-table-column>
        <el-table-column prop="app_name" label="执行器"></el-table-column>
        <el-table-column prop="ip" label="分片IP" width="180"></el-table-column>
        <el-table-column prop="port" label="分片port" width="180"></el-table-column>
        <el-table-column prop="extend" label="分片的扩展业务字段" width="180"></el-table-column>
        <el-table-column prop="orde" label="排序" width="200"></el-table-column>
        <el-table-column fixed="right" label="操作" width="100">
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="updateRow(scope.row)">编辑</el-button>
            <el-button type="text" size="small" @click="deleteRow(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!-- 列表  END -->
    <div v-if="showB" style="width: 50%;margin-left: 20%;">
      <el-form ref="form" :model="form" label-width="120px">
        <el-form-item label="执行器">
          <el-select v-model="form.app_name" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="分片名称">
          <el-input v-model="form.shard_name" placeholder="请输入分片名称"></el-input>
        </el-form-item>
        <el-form-item label="分片IP">
          <el-input v-model="form.ip" placeholder="请输入分片IP"></el-input>
        </el-form-item>
        <el-form-item label="分片端口">
          <el-input v-model="form.port" placeholder="请输入分片端口"></el-input>
        </el-form-item>
        <el-form-item label="分片业务字段">
          <el-input v-model="form.extend" placeholder="请输入分片业务字段数据"></el-input>
        </el-form-item>
        <el-form-item label="分片排序">
          <el-input v-model="form.orde" placeholder="请输入分片排序"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">保存</el-button>
          <el-button @click="cancel()">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
import qs from "qs";
import { Message } from "element-ui";
export default {
  name: "MyIpShardSettinng",
  data() {
    return {
      tableData: [],
      showA: true,
      showB: false,
      options: [],
      value: "",
      form: {
        id: "",
        shard_name: "",
        app_name: "",
        orde: "",
        ip: "",
        port: "",
        extend: ""
      },
      dateValue: ""
    };
  },
  mounted() {
    this.getXxlJobGroups();
    this.getShardList();
  },
  methods: {
    mySelect(item) {
      console.log();
      this.$axios
        .get("shard/myShardList?" + qs.stringify(this.value))
        .then(data => {
          console.log(data);
          this.tableData = data.data.myShards;
        });
    },
    chengShowValue() {
      this.showA = false;
      this.showB = true;
      this.form = {
        id: "",
        shard_name: "",
        app_name: "",
        order: "",
        ip: "",
        port: "",
        extend: ""
      };
      // 加载下拉选的数据
      this.getXxlJobGroups();
    },
    updateRow(row) {
      this.showA = false;
      this.showB = true;
      this.form = row;
    },
    deleteRow(row) {
      const id = { id: row.id };
      const that = this;
      this.$axios.delete("/shard/delete?" + qs.stringify(id)).then(data => {
        if (data.data.code == "1") {
          Message.success(data.data.msg);
          if (that.value != "") {
            that.mySelect(that.value);
          } else {
            that.getShardList();
          }
        } else {
          Message.error(data.data.msg);
        }
      });
    },
    onSubmit() {
      this.showA = true;
      this.showB = false;
      const shard = this.form;
      const that = this;
      if (shard.id == "") {
        // add
        if (shard.shard_name == "") {
          Message.warning("分片名称不能为空！");
        } else if (shard.app_name == "") {
          Message.warning("执行器名称不能为空！");
        } else if (shard.ip == "") {
          Message.warning("IP不能为空！");
        } else if (shard.port == "") {
          Message.warning("端口号不能为空！");
        } else if(shard.extend==""){
          Message.warning("分片业务字段不能为空！");
        }else {
          this.$axios.post("/shard/save?" + qs.stringify(shard)).then(data => {
            if (data.data.code == "1") {
              Message.success(data.data.msg);
              if (that.value != "") {
                that.mySelect(that.value);
              } else {
                that.getShardList();
              }
            } else {
              Message.error(data.data.msg);
            }
          });
        }
      } else {
        if (shard.shard_name == "") {
          Message.warning("分片名称不能为空！");
        } else if (shard.app_name == "") {
          Message.warning("执行器名称不能为空！");
        } else if (shard.ip == "") {
          Message.warning("IP不能为空！");
        } else if (shard.port == "") {
          Message.warning("端口号不能为空！");
        } else if(shard.extend==""){
          Message.warning("分片业务字段不能为空！");
        } else {
          this.$axios.put("/shard/update?" + qs.stringify(shard)).then(data => {
            if (data.data.code == "1") {
              Message.success(data.data.msg);
              if (that.value != "") {
                that.mySelect(that.value);
              } else {
                that.getShardList();
              }
            } else {
              Message.error(data.data.msg);
            }
          });
        }
      }
    },
    cancel() {
      this.showA = true;
      this.showB = false;
      if (that.value != "") {
        that.mySelect(that.value);
      } else {
        that.getShardList();
      }
    },
    getShardList() {
      const that = this;
      this.$axios.get("/shard/getShardList").then(data => {
        if (data.data.code == "1") {
          that.tableData = data.data.shardList;
        } else {
          Message.error(data.data.msg);
        }
      });
    },
    getXxlJobGroups() {
      const that = this;
      this.$axios.get("/shard/xxlJobGroupList").then(data => {
        if (data.data.code == "1") {
          that.options = [];
          // success
          data.data.xxlJobGroups.forEach(element => {
            const e = {
              value: element.appName,
              label: element.title
            };
            that.options.push(e);
          });
        }
      });
    }
  }
};
</script>

