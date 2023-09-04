from flask import Flask, request, render_template


app = Flask(__name__)


def pvo(param):
    return param


# True positive
@app.route('/param1')
def main_param():
    param = request.args.get("param") or ''
    # True positive
    return render_template('index.html', param=param)


# False negative
@app.route('/param2')
def false_param():
    param = request.args.get("param") or ''
    f = pvo(param)
    # Analyzers that do not interpret the execution flow based on functional data flows will not report a vulnerability
    # False negative
    return render_template('index.html', param=f)


if __name__ == '__main__':
    app.run(port=8080)

