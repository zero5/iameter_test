from flask import Flask, request, render_template


app = Flask(__name__)


def filter_req(data):
    return data.replace('<', '').replace('>', '')


# True positive
@app.route('/param1')
def main_param():
    cond_true = 'true'
    if f'{cond_true}' == 'true':
        param = request.args.get("param") or ''
        # True positive
        return render_template('index.html', param=param)


# False positive
@app.route('/param2')
def false_param():
    cond = 'ZmFsc2U='
    param = request.args.get("param") or ''
    if (cond + 'true') == 'true':
        # An analyzer that does not interpret the semantics of standard library types will report a vulnerability here
        # False positive
        return render_template('index.html', param=param)
    return 'Hello'


# False positive
@app.route('/param3')
def false_param3():
    cond = 'ZmFsc2U='
    param = request.args.get("param") or ''
    if ''.join([cond, 'true']) == 'true':
        # An analyzer that does not interpret the semantics of standard library types will report a vulnerability here
        # False positive
        return render_template('index.html', param=param)
    return 'Hello'


# False positive
@app.route('/param4')
def false_param4():
    cond = 'ZmFsc2U='
    param = request.args.get("param") or ''
    if '{}{}'.format(cond, 'true') == 'true':
        # An analyzer that does not interpret the semantics of standard library types will report a vulnerability here
        # False positive
        return render_template('index.html', param=param)
    return 'Hello'


# False positive
@app.route('/param5')
def false_param5():
    cond = 'ZmFsc2U='
    param = request.args.get("param") or ''
    if f'{cond} true' == 'true':
        # An analyzer that does not interpret the semantics of standard library types will report a vulnerability here
        # False positive
        return render_template('index.html', param=param)
    return 'Hello'


if __name__ == '__main__':
    app.run(port=8080)

