from flask import Flask, request, render_template


app = Flask(__name__)


# True positive
@app.route('/param1')
def main_param():
    if 'false' == 'false':
        param = request.args.get("param") or ''
        # An analyzer that ignores execution point reachability conditions will report a vulnerability here
        # True positive
        return render_template('index.html', param=param)


# False positive
@app.route('/param2')
def false_param():
    cond1 = 'ZmFsc2U='
    if cond1 == 'false':
        param = request.args.get("param") or ''
        # An analyzer that ignores execution point reachability conditions will report a vulnerability here
        # False positive
        return render_template('index.html', param=param)
    return 'Hello'


if __name__ == '__main__':
    app.run(port=8080)

