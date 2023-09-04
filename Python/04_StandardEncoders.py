from flask import Flask, request, render_template
from html import escape


app = Flask(__name__)


# True positive
@app.route('/param1')
def main_param():
    param = request.args.get("param") or ''
    # True positive
    render_template('index.html', param=param)


# False negative
@app.route('/param2')
def false_param():
    param = escape(request.args.get("param")) or ''
    # An analyzer that ignores the semantics of standard filter functions will report a vulnerability here
    # False positive
    render_template('index.html', param=param)


if __name__ == '__main__':
    app.run(port=8080)

