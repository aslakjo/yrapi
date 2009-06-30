require 'rubygems'
require 'sinatra'
require 'haml'

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
    haml :index
  end
end

get '/mobilyr.jar' do
  send_file "mobilyr.jar"
end
