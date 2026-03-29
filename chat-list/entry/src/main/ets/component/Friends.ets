@Component
export struct Friends {
  pic: string = ""
  name: string = ""
  build() {

    ListItem() {
      Row({ space: 10 }) {
        Image($r(this.pic)).width(65).height(65).borderRadius(10)
        Text(this.name)
      }.margin({ top: 10, left: 15 }).width("100%")

    }
  }
}
//计应腾2503
//20251204118
//张文茜
