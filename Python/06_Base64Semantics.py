from flask import Flask, request, render_template
import base64


app = Flask(__name__)


# True positive
@app.route('/param1')
def main_param():
    cond = 'ZmFsc2U='

    # True positive
    if 'true' == 'true':
        param = request.args.get("param") or ''
        return render_template('index.html', param=param)


# False negative
@app.route('/param2')
def false_param():
    cond = 'ZmFsc2U='

    if base64.standard_b64decode(cond) == b'true':
        param = request.args.get("param") or ''
        # Analyzers that do not take into account the semantics of standard encoding functions
        # will report a vulnerability
        # False positive
        return render_template('index.html', param=param)
    return 'Hello'


if __name__ == '__main__':
    app.run(port=8080)

