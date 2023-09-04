from flask import Flask, request, render_template


app = Flask(__name__)


# True positive
@app.route('/param1')
def main_param():
    param = request.args.get("param") or ''
    # True positive
    return render_template('index.html', param=param)


# False negative
@app.route('/param2')
def false_param():
    param = str(int(request.args.get("param"))) or ''
    # Analyzers that do not take into account the semantics of standard encoding functions will report a vulnerability
    # False positive
    return render_template('index.html', param=param)


if __name__ == '__main__':
    app.run(port=8080)

