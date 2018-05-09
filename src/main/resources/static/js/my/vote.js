var Top = function (props) {
    return (
        <div className="topDiv">
            <span className="wolcome">欢迎登陆</span>
            <span className="date">时间：{props.date.toLocaleTimeString()}</span>
        </div>
    );
}

var topText = document.querySelector('#top');
ReactDOM.render(
    <Top date={new Date()}/>,
    topText
);

var HelloWord = function () {
    return (
        <h1>投票系统</h1>
    );
}

var content = document.querySelector("#content");
ReactDOM.render(
    <HelloWord/>,
    content
);