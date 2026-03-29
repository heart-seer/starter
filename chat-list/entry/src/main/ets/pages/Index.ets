import { Friends } from '../component/Friends'

@Entry
@Component
struct Index {
  build() {
    Column() {
      Text("聊天").fontSize(23).fontWeight(FontWeight.Bold).width("100%").textAlign(TextAlign.Center)

      List({ space: 10 }) {
        Friends({pic:"app.media.img",name:"爱吃糖的猫"})
        Friends({pic:"app.media.img_1",name:"晚风轻轻"})
        Friends({pic:"app.media.img_2",name:"木子"})
        Friends({pic:"app.media.img_3",name:"干饭第一名"})
        Friends({pic:"app.media.img_4",name:"喜欢发呆"})
        Friends({pic:"app.media.img_5",name:"憨憨本憨"})
        Friends({pic:"app.media.img_6",name:"晚风斩月"})
        Friends({pic:"app.media.img_7",name:"山野有来信"})
        Friends({pic:"app.media.img_8",name:"半勺自愈"})
        Friends({pic:"app.media.img_9",name:"越过人海寻风"})
        Friends({pic:"app.media.img_10",name:"游离在月光外"})
      }.width("100%").height("100%").scrollBar(BarState.On)
    }.width("100%").height("100%")
  }
}

