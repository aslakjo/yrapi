require 'java'
include Java

require 'webrick'
include WEBrick

server_jar = "../target/yr-api-jar-with-dependencies" || ENV['SERVER_JAR']

require server_jar
include_class "no.yr.LocationApi"
include_class "no.yr.svg.TimeSeriesRendrer"

class SvgServlet < HTTPServlet::AbstractServlet
  def do_GET(req, res)
    lat = nil
    lon = nil
    height = 0
    begin
      lat, lon, height = process(req)
    rescue Exception => e
      res.status = 400 
      res.body ="# Error: " + e
      res['Content-Type'] = "text"
      return
    end
        
    api_result = LocationApi.new()
    api_result.setLatitude(lat.to_f)
    api_result.setLongitude(lon.to_f)
    api_result.setMoh(height.to_i)
    
    timeseries = api_result.fetchTimeseriesForHours(24)
    
    res.status = 200
    res.body = TimeSeriesRendrer.new(timeseries).getXml
    res['Content-Type'] = " image/svg+xml"
  end
  
  
  
  def process(request)
    raise "lat and lon must be set" unless not request.query['lat'] == nil and not request.query['lon'] == nil
    
    if request.query['height'] == nil
      return request.query['lat'], request.query['lon'], 0
    else
      return request.query['lat'], request.query['lon'], request.query['height']
    end
        
  end
end
