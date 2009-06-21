require 'rubygems'
require 'sinatra'

require File.dirname(__FILE__) +  '/svg_servlet' 

get '/' do
  begin
    servlet = SvgServlet.new()
    res = {}
    servlet.do_GET(params, res)
    status 200
    res["body"]
  rescue Exception => e
    status 400
    "<pre>#{e.message} #{e.cause} #{e.backtrace}</pre>"
  end
end
